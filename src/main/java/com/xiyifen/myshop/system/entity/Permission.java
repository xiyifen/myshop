package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sp_permission")
@ApiModel(value="Permission对象", description="权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "ps_id", type = IdType.AUTO)
    private Integer psId;

    @ApiModelProperty(value = "权限名称")
    private String psName;

    @ApiModelProperty(value = "父id")
    private Integer psPid;

    @ApiModelProperty(value = "控制器")
    private String psC;

    @ApiModelProperty(value = "操作方法")
    private String psA;

    @ApiModelProperty(value = "权限等级")
    private String psLevel;

    @ApiModelProperty(value = "权限标识")
    private String perms;


}
