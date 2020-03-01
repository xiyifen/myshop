package com.xiyifen.myshop.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
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
@TableName("sp_report_2")
@ApiModel(value="Report2对象", description="")
public class Report2 implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String rp2Page;

    private Integer rp2Count;

    private Date rp2Date;


}
