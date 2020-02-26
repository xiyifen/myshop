package com.xiyifen.myshop.system.controller;


import com.xiyifen.myshop.common.result.ResponseResult;
import com.xiyifen.myshop.system.entity.Permission;
import com.xiyifen.myshop.system.mapper.PermissionMapper;
import com.xiyifen.myshop.system.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiyifen
 */
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/rights/{type}")
    public ResponseResult getPermissonList(@PathVariable(value = "type") String type) {
        ResponseResult result = new ResponseResult();
        List<Permission> permissionList ;
        permissionList = permissionService.getBaseMapper().selectList(null);
        if (StringUtils.equals("list", type)) {
            result.success("获取权限列表成功", permissionList);
        }
        if (StringUtils.equals("tree", type)) {
            //
            List<Permission> topPs = new ArrayList<>();
            for (Permission node : permissionList) {
                // 保存顶级菜单
                if (node.getPid() == 0) {
                    topPs.add(node);
                    continue;
                }
                // 为其余菜单找到其父节点，并添加到父节点中
                for (Permission parent : permissionList) {
                    if (node.getPid() == parent.getId()) {
                        if (parent.getChildren() == null) {
                            parent.initChildren();
                        }
                        parent.getChildren().add(node);
                    }
                }
            }

            result.success("获取权限列表成功",topPs);
        }

        return result;
    }

    @GetMapping("/menus")
    public ResponseResult getMenus(){
        List<Permission> permissionList = permissionService.getBaseMapper().selectList(null);
        List<Permission> topPs=new ArrayList<>();

        permissionList.forEach(permission -> {
            if (permission.getPid()==0){
                permission.initChildren();
                topPs.add(permission);
            }
        });
        for (Permission permission:topPs){
            permissionList.forEach(node ->{
                if (node.getPid()==permission.getId()){
                    permission.getChildren().add(node);
                }
            } );
        }

        return new ResponseResult().success("获取菜单列表成功",topPs);


    }

}
