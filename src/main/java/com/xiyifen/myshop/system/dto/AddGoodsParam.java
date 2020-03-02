package com.xiyifen.myshop.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "添加商品实体")
public class AddGoodsParam {

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "分类列表")
    private String goodsCat;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "商品重量")
    private Integer goodsWeight;

    @ApiModelProperty(value = "商品详情介绍")
    private String goodsIntroduce;


}
