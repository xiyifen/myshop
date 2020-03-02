package com.xiyifen.myshop.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.system.entity.Manager;
import com.xiyifen.myshop.system.entity.Permission;
import com.xiyifen.myshop.system.mapper.ManagerMapper;
import com.xiyifen.myshop.system.mapper.PermissionMapper;
import com.xiyifen.myshop.system.service.ManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xiyifen
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ManagerMapper managerMapper;


    @Override
    public Set<String> getUserPermissions(List<String> pId) {
        List<Integer> list=new ArrayList<>();
        for (String item:pId){
            list.add(Integer.valueOf(item));
        }
        List<Permission> permissions = permissionMapper.selectBatchIds(list);
        Set<String> result=new TreeSet<>();
        for (Permission permission:permissions){
            if (null!=permission.getPerms()&&permission.getPerms().length()>0)
                result.add(permission.getPerms());
        }
        return result;
    }

    @Override
    public Map<String,Object> findManagers(String query, QueryRequest request) {
        Map<String,Object> resultMap=new HashMap<>();
        Page<Manager> page=new Page<>(request.getPagenum(),request.getPagesize());
//        QueryWrapper<Manager> queryWrapper=new QueryWrapper<>();
//        if (StringUtils.isNotBlank(query)){
////            wrapper = new LambdaQueryWrapper<Manager>().like(Manager::getMgName, query);
//            queryWrapper.lambda().like(Manager::getUsername,query);
//        }
//        Page<Manager> pageinfo = managerMapper.selectPage(page, queryWrapper);

        IPage<Manager> pageinfo = managerMapper.getMappersInfo(page, query);
        List<Manager> managerList = pageinfo.getRecords();
        resultMap.put("total",pageinfo.getTotal());
        resultMap.put("pagenum",pageinfo.getCurrent());
        resultMap.put("users",managerList);
        return resultMap;
    }


}
