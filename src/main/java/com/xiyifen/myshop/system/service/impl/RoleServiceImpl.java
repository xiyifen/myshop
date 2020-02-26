package com.xiyifen.myshop.system.service.impl;

import com.xiyifen.myshop.system.entity.Permission;
import com.xiyifen.myshop.system.entity.Role;
import com.xiyifen.myshop.system.mapper.PermissionMapper;
import com.xiyifen.myshop.system.mapper.RoleMapper;
import com.xiyifen.myshop.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyifen
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * 获取所有角色的权限，格式化成树的形式
     *
     * @return
     */
    //
    @Override
    public List<Role> getRoleTree(Integer roleId) {
        List<Role> roleList=new ArrayList<>();
        if (roleId==null){
         roleList = roleMapper.selectList(null);
        }else {
            roleList.add(roleMapper.selectById(roleId));
        }
        for (Role role : roleList) {
            String[] psId = role.getPsIds().split(",");
            List<Permission> permissionList = permissionMapper.selectBatchIds(Arrays.asList(psId));
            role.initChildren();
            for (Permission permission : permissionList) {
                // 判断是否是以及权限或者二级权限，其余等级权限为叶子节点，不用初始化
                if (permission.getLevel().equals("0")||permission.getLevel().equals("1")){
                    permission.initChildren();
                }
                if (permission.getPid() == 0) {
                    role.getChildren().add(permission);
                    continue;
                }
                for (Permission parent : permissionList) {
                    System.out.println(parent.getId().equals(permission.getPid()));
                    if (parent.getId().equals(permission.getPid())) {
                        if (parent.getChildren() == null) {
                            parent.initChildren();
                        }
                        parent.getChildren().add(permission);
                    }
                }

            }
//            role.initChildren();
//            role.getChildren().add(topPs);
        }
        return roleList;
    }

    /**
     *
     * @param roleId
     * @param rightId
     * @return
     */
    @Override
    public List<Permission> deleteRoleRights(Integer roleId, Integer rightId) {

        Role role = roleMapper.selectById(roleId);
        List<Permission> permissionList = permissionMapper.selectList(null);
        List<Integer> psIds=new ArrayList<>();
        psIds.add(rightId);
        // 递归查找要删除权限的子权限，并将其id存到psIds中
        getPsId(psIds,permissionList,rightId);
        // 角色的旧权限id
        List<String> strIdsList=new ArrayList<>(Arrays.asList(role.getPsIds().split(",")));
        List<Integer> rolePsId = strIdsList.stream().map(Integer::parseInt).collect(Collectors.toList());
        // 遍历要psIds，即要删除的Ids，将其从旧权限ids中删除，然后在持久化到数据库中
        for (Integer item:psIds){
            if (rolePsId.contains(item)){
                rolePsId.remove(item);
            }
        }
        // 用“，”连接后保存
        String sPs = rolePsId.stream().map(String::valueOf).collect(Collectors.joining(","));
        role.setPsIds(sPs);
        roleMapper.updateById(role);
        // 重新查询此角色的权限
        List<Role> roleTree = this.getRoleTree(roleId);
        List<Permission> permissions = roleTree.get(0).getChildren();
        return permissions;
    }
    // 查找当前rightId 的子权限Id
    private void getPsId(List<Integer> ids,List<Permission> permissionList,Integer rightId){
        for (Permission permission:permissionList){
            if (permission.getPid().equals(rightId)){
                ids.add(permission.getId());
                getPsId(ids,permissionList,permission.getId());
            }
        }
    }
}
