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
 * 收货人表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_consignee")
@ApiModel(value="Consignee对象", description="收货人表")
public class Consignee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
            @TableId(value = "cgn_id", type = IdType.AUTO)
    private Integer cgnId;

    @ApiModelProperty(value = "会员id")
    private Integer userId;

    @ApiModelProperty(value = "收货人名称")
    private String cgnName;

    @ApiModelProperty(value = "收货人地址")
    private String cgnAddress;

    @ApiModelProperty(value = "收货人电话")
    private String cgnTel;

    @ApiModelProperty(value = "邮编")
    private String cgnCode;

    @ApiModelProperty(value = "删除时间")
    private Integer deleteTime;


}
