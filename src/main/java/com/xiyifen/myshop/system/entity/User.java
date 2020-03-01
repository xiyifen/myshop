package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 会员表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_user")
@ApiModel(value="User对象", description="会员表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
            @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "登录名")
    private String username;

    @ApiModelProperty(value = "qq官方唯一编号信息")
    private String qqOpenId;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "新用户注册邮件激活唯一校验码")
    private String userEmailCode;

    @ApiModelProperty(value = "新用户是否已经通过邮箱激活帐号")
    private String isActive;

    @ApiModelProperty(value = "性别")
    private String userSex;

    @ApiModelProperty(value = "qq")
    private String userQq;

    @ApiModelProperty(value = "手机")
    private String userTel;

    @ApiModelProperty(value = "学历")
    private String userXueli;

    @ApiModelProperty(value = "爱好")
    private String userHobby;

    @ApiModelProperty(value = "简介")
    private String userIntroduce;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    @ApiModelProperty(value = "修改时间")
    private Integer updateTime;


}
