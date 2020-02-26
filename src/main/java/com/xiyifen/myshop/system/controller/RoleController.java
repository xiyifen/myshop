package com.xiyifen.myshop.system.controller;


import com.xiyifen.myshop.common.result.ResponseResult;
import com.xiyifen.myshop.system.entity.Permission;
import com.xiyifen.myshop.system.entity.Role;
import com.xiyifen.myshop.system.service.RoleService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author xiyifen
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseResult getRoles(){
        List<Role> roleTree = roleService.getRoleTree(null);
        return new ResponseResult().success("获取成功",roleTree);
    }


    @PostMapping
    public ResponseResult addRole(@RequestBody Role role){

        roleService.save(role);
        return new ResponseResult().success("创建成功",role);
    }

    @GetMapping("/{rid}")
    public ResponseResult findRoleById(@PathVariable(value = "rid") Integer rid){
        Role role = roleService.getById(rid);
        return new ResponseResult().success("查询成功",role);
    }

    @PutMapping("/{rid}")
    public ResponseResult updateRole(@PathVariable(value = "rid",required = true)Integer rid,@RequestBody Role role){
        role.setRoleId(rid);
        roleService.updateById(role);

        return new ResponseResult().success("编辑成功",role);
    }

    @DeleteMapping("/{rid}")
    public ResponseResult deleteRole(@PathVariable(value = "rid",required = true) Integer rid){
        roleService.removeById(rid);

        return new ResponseResult().success("删除成功",null);
    }


    @PostMapping("/{roleId}/rights")
    @ApiOperation(value = "为角色分配权限")
    public ResponseResult authRole(@PathVariable(value = "roleId",required = true)Integer roleId, @RequestBody Map<String,String> map){
        Role role=new Role();
        role.setRoleId(roleId);
        role.setPsIds(map.get("pids"));
        roleService.updateById(role);

        return new ResponseResult().success("更新成功",role);
    }

    @DeleteMapping("/{roleId}/rights/{rightId}")
    public ResponseResult deleteRolePermission(@PathVariable(value = "roleId") Integer roleId,
                                               @PathVariable(value = "rightId") Integer rightId){

        List<Permission> permissions = roleService.deleteRoleRights(roleId, rightId);
        return new ResponseResult().success("删除成功",permissions);

    }
}
