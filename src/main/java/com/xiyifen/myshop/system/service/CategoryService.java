package com.xiyifen.myshop.system.service;

import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.system.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author xiyifen
 */
public interface CategoryService extends IService<Category> {

    /**
     * 构造为商品分类树
     * @param type
     * @param request
     * @return
     */
    Map<String,Object> getCategories(Integer type, QueryRequest request);


}
