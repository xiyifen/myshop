package com.xiyifen.myshop.common.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiyifen.myshop.common.jwt.JWTUtil;
import com.xiyifen.myshop.system.entity.Manager;
import com.xiyifen.myshop.system.entity.Role;
import com.xiyifen.myshop.system.mapper.RoleMapper;
import com.xiyifen.myshop.system.service.ManagerService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

//import com.xiyifen.myshop.system.entity.Permission;
//import com.xiyifen.myshop.system.entity.Role;
//import com.xiyifen.myshop.system.entity.User;
//import com.xiyifen.myshop.system.mapper.PermissionMapper;
//import com.xiyifen.myshop.system.mapper.RoleMapper;
//import com.xiyifen.myshop.system.mapper.UserMapper;

public class ShiroRealm extends AuthorizingRealm {
    
//    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
//    @Autowired
//    private MenuMapper menuMapper;

//    @Autowired
//    private UserService userService;
    @Autowired
    private ManagerService managerService;
    
    /**
     * 获取用户角色和权限
     * @param principalCollection  实际上就是前端传回的token
     * @return
     */
   @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       //对principalCollection解密，取出username
       String username = JWTUtil.getUsername(String.valueOf(principalCollection));
        System.out.println("用户" + username + "获取权限：doGetAuthorizationInfo");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 根据用户名获取用户角色
        List<Role> roleList = roleMapper.getRolesByUsername(username);
        Set<String> roleSet = new HashSet<>();
        List<String> pIdList=new ArrayList<>();
        for (Role role : roleList) {
            roleSet.add(role.getRoleName());
            String[] pId = role.getPsIds().split(",");
            pIdList=Arrays.asList(pId);
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
//        List<Menu> permissionList = userService.findByUsername(username);
//        Set<String> menuSet = new HashSet<>();
//        for (Menu menu : permissionList) {
//            //permission.getName() 权限通配符
//            menuSet.add(menu.getMenuName());
//        }
       // 获取权限集
       Set<String> userPermissions = managerService.getUserPermissions(pIdList);
       simpleAuthorizationInfo.setStringPermissions(userPermissions);
//        simpleAuthorizationInfo.setStringPermissions(menuSet);

        return simpleAuthorizationInfo;

    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
   @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)  {
       // 这里的authenticationToken是从JWTFilter的executeLogin传递过来的，已经经过了解密
       String token = (String) authenticationToken.getCredentials();

       String username = JWTUtil.getUsername(token);
       if (StringUtils.isBlank(username)) {
           throw new AuthenticationException("token校验不通过1");
       }
       // 通过用户名查询用户信息
       Manager user = managerService.getOne(new LambdaQueryWrapper<Manager>().eq(Manager::getUsername,username));
       if (user == null) {
           throw new AuthenticationException("用户名或密码错误");
       }
       if (!JWTUtil.verify(token, username, user.getPassword())) {
           throw new AuthenticationException("token校验不通过2");
       }

       SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token, token, "febs_shiro_realm");
       return simpleAuthenticationInfo;
    }
}
