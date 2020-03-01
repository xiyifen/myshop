package com.xiyifen.myshop.system.dto;

import lombok.Data;

@Data
public class AttributeParam {

    private String attrName;
    // 动态或静态
    private String attrSel;

    private String attrVals;
}
