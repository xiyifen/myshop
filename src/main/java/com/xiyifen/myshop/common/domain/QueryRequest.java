package com.xiyifen.myshop.common.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QueryRequest {

    @NotNull(message = "pagenum不能为空")
    private int pagenum;
    @NotNull(message = "pagesize不能为空")
    private int pagesize;

    private String sortOrder;
    private String sortField;

}
