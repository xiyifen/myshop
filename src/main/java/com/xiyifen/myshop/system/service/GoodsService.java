package com.xiyifen.myshop.system.service;

import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.system.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author xiyifen
 */
public interface GoodsService extends IService<Goods> {

    Map<String,Object> getGoodsList(String query, QueryRequest request);

}
