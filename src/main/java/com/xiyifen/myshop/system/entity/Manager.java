package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 管理员表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sp_manager")
@ApiModel(value="Manager对象", description="管理员表")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
            @TableId(value = "mg_id", type = IdType.AUTO)
    private Integer mgId;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "name不能为空")
    private String mgName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mgPwd;

    @ApiModelProperty(value = "注册时间")
    private Long mgTime;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    private String mgMobile;

    private String mgEmail;

    @ApiModelProperty(value = "1：表示启用 0:表示禁用")
    private Integer mgState;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

}
