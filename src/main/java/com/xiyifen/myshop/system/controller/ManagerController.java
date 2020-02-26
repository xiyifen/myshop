package com.xiyifen.myshop.system.controller;


import com.xiyifen.myshop.common.result.ResponseResult;
import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.system.entity.Manager;
import com.xiyifen.myshop.system.entity.Role;
import com.xiyifen.myshop.system.service.ManagerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author xiyifen
 */
@RestController
@Validated
@RequestMapping("/users")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping()
    @RequiresPermissions("user:view")
    public ResponseResult getUserInfo(String query,@Valid QueryRequest request){

        Map<String, Object> managers = managerService.findManagers(query, request);
        return new ResponseResult().success("获取成功",managers);
    }

    @PostMapping()
    @RequiresPermissions("user:add")
    public ResponseResult addUser(@RequestBody @Valid Manager manager){
        manager.setCreateTime(System.currentTimeMillis()/1000);
        managerService.save(manager);
        return new ResponseResult().success("用户创建成功",manager);
    }

    @PutMapping("/{uid}/state/{type}")
    public ResponseResult updateUserState(@PathVariable(value = "uid",required = true) Integer uid,@PathVariable(value = "type",required = true) Boolean type){
        Manager manager = managerService.getById(uid);
        manager.setState(type?1:0);
        managerService.saveOrUpdate(manager);

        return new ResponseResult().success("用户状态修改成功",manager);
    }

    @GetMapping("/{id}")
    public ResponseResult getManageInfo(@PathVariable(value = "id",required =true ) Integer id){
        Manager manager = managerService.getById(id);
        manager.setPassword(null);
        return new ResponseResult().success("查询成功",manager);
    }

    @PutMapping("/{id}")
    public ResponseResult updateManager(@PathVariable(value = "id") Integer id,@RequestBody Manager manager){
        manager.setId(id);
        managerService.updateById(manager);
         manager = managerService.getById(id);
        return new ResponseResult().success("更新成功",manager);
    }

    @PutMapping("/{mid}/role")
    public ResponseResult setManagerRole(@PathVariable(value = "mid") Integer mid,@RequestBody Role role){
        System.out.println(mid+"  "+ role.getRoleId());
        Manager manager=new Manager();
        manager.setId(mid);
        manager.setRoleId(role.getRoleId());
        managerService.updateById(manager);
         manager = managerService.getById(manager.getId());
        return new ResponseResult().success("设置角色成功",manager);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteManager(@PathVariable(value = "id",required = true) Integer id){
        managerService.removeById(id);

        return new ResponseResult().success("删除成功",null);
    }
}
