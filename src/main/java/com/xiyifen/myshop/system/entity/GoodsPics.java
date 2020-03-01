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
 * 商品-相册关联表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_goods_pics")
@ApiModel(value="GoodsPics对象", description="商品-相册关联表")
public class GoodsPics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
            @TableId(value = "pics_id", type = IdType.AUTO)
    private Integer picsId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "相册大图800*800")
    private String picsBig;

    @ApiModelProperty(value = "相册中图350*350")
    private String picsMid;

    @ApiModelProperty(value = "相册小图50*50")
    private String picsSma;


}
