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
 * 快递表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_express")
@ApiModel(value="Express对象", description="快递表")
public class Express implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
            @TableId(value = "express_id", type = IdType.AUTO)
    private Integer expressId;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "订单快递公司名称")
    private String expressCom;

    @ApiModelProperty(value = "快递单编号")
    private String expressNu;

    @ApiModelProperty(value = "记录生成时间")
    private Integer createTime;

    @ApiModelProperty(value = "记录修改时间")
    private Integer updateTime;


}
