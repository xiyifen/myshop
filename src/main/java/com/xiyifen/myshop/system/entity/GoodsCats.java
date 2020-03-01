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
 * 
 *
 * @author xiyifen
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Accessors(chain = true)
@TableName("sp_goods_cats")
@ApiModel(value="GoodsCats对象", description="")
public class GoodsCats implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类id")
            @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer catId;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "分类名称")
    private String catName;

    @ApiModelProperty(value = "是否显示")
    private Integer isShow;

    @ApiModelProperty(value = "分类排序")
    private Integer catSort;

    @ApiModelProperty(value = "数据标记")
    private Integer dataFlag;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;


}
