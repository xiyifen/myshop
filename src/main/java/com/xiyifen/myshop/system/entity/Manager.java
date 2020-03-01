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

/**
 * 管理员表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_manager")
@ApiModel(value="Manager对象", description="管理员表")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "注册时间")
    private Long createTime;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    private String mobile;

    private String email;

    @ApiModelProperty(value = "1：表示启用 0:表示禁用")
    private Integer state;


    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleName;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;


}
