package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
// @Accessors(chain = true)
@TableName("sp_user_cart")
@ApiModel(value="UserCart对象", description="")
public class UserCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
            @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    @ApiModelProperty(value = "学员id")
    private Integer userId;

    @ApiModelProperty(value = "购物车详情信息，二维数组序列化信息")
    private String cartInfo;

    private Date createdAt;

    private Date updatedAt;

    private Date deleteTime;


}
