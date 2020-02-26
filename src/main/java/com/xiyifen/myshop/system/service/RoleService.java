package com.xiyifen.myshop.system.service;

import com.xiyifen.myshop.system.entity.Permission;
import com.xiyifen.myshop.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xiyifen
 */
public interface RoleService extends IService<Role> {

    List<Role> getRoleTree(Integer roleId);

    public List<Permission> deleteRoleRights(Integer roleId, Integer rightId) ;

}
