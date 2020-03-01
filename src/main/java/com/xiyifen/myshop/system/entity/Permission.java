package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_permission")
@ApiModel(value="Permission对象", description="权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    private String authName;

    @ApiModelProperty(value = "父id")
    private Integer pid;

    @ApiModelProperty(value = "控制器")
    private String psC;

    @ApiModelProperty(value = "操作方法")
    private String psA;

    @ApiModelProperty(value = "权限等级")
    private String level;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "类型 0菜单 1按钮")
    private Character type;

    @TableField(exist = false)
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Permission> children;

    public void initChildren(){
        children=new ArrayList<>();
    }

}
