package com.xiyifen.myshop.system.mapper;

import com.xiyifen.myshop.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author xiyifen
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户名查询角色信息
     * @param username
     * @return
     */
    List<Role> getRolesByUsername(String username);
}
