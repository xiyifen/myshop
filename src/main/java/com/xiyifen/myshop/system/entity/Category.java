package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@TableName("sp_category")
@ApiModel(value="Category对象", description="")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类唯一ID")
            @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer catId;

    @ApiModelProperty(value = "分类名称")
    private String catName;

    @ApiModelProperty(value = "分类父ID")
    private Integer catPid;

    @ApiModelProperty(value = "分类层级 0: 顶级 1:二级 2:三级")
    private Integer catLevel;

    @ApiModelProperty(value = "是否删除 1为删除")
    private Integer catDeleted;

    private String catIcon;

    private String catSrc;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private List<Category> children;

    public void initChildren() {
        children=new ArrayList<>();
    }

}
