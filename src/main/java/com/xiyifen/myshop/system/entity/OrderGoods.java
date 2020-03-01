package com.xiyifen.myshop.system.entity;

import java.math.BigDecimal;
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
 * 商品订单关联表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_order_goods")
@ApiModel(value="OrderGoods对象", description="商品订单关联表")
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "购买单个商品数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "商品小计价格")
    private BigDecimal goodsTotalPrice;


}
