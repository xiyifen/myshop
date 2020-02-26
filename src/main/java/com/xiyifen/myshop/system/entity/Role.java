package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sp_role")
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "权限ids,1,2,5")
    private String psIds;

    @ApiModelProperty(value = "控制器-操作,控制器-操作,控制器-操作")
    private String psCa;

    private String roleDesc;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Permission> children;

    public void initChildren(){
        children=new ArrayList<>();
    }

}
