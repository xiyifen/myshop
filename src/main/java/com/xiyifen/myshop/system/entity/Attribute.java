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
 * 属性表
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_attribute")
@ApiModel(value="Attribute对象", description="属性表")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
            @TableId(value = "attr_id", type = IdType.AUTO)
    private Integer attrId;

    @ApiModelProperty(value = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "外键，类型id")
    private Integer catId;

    @ApiModelProperty(value = "only:输入框(唯一)  many:后台下拉列表/前台单选框")
    private String attrSel;

    @ApiModelProperty(value = "manual:手工录入  list:从列表选择")
    private String attrWrite;

    @ApiModelProperty(value = "可选值列表信息,例如颜色：白色,红色,绿色,多个可选值通过逗号分隔")
    private String attrVals;

    @ApiModelProperty(value = "删除时间标志")
    private Integer deleteTime;


}
