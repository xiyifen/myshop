package com.xiyifen.myshop.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiyifen.myshop.common.exception.MyException;
import com.xiyifen.myshop.common.jwt.JWTUtil;
import com.xiyifen.myshop.common.result.ResponseResult;
import com.xiyifen.myshop.common.utils.MD5Util;
import com.xiyifen.myshop.system.entity.Manager;
import com.xiyifen.myshop.system.dto.UserParam;
import com.xiyifen.myshop.system.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private ManagerService managerService;


    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation(value = "登录接口")
    public ResponseResult login(@RequestBody UserParam userBo) throws MyException {
        String username = StringUtils.lowerCase(userBo.getUsername());
        String password= MD5Util.encrypt(username,userBo.getPassword());
        Manager user = managerService.getOne(new LambdaQueryWrapper<Manager>().eq(Manager::getUsername, username));
        if (user==null)
            throw new MyException("用户名或密码错误");
        if (!StringUtils.equals(password,user.getPassword()))
            throw new MyException(("用户名或密码错误"));
        if (0==user.getState())
            throw new LockedAccountException("用户已被锁定，请联系管理员！");

        // 生成token
        String token = JWTUtil.sign(username, password);
        user.setToken(token);
        return new ResponseResult().success("登录成功",user);
    }


}
