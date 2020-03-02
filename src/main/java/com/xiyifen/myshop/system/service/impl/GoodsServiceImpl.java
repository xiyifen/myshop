package com.xiyifen.myshop.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.system.entity.Goods;
import com.xiyifen.myshop.system.mapper.GoodsMapper;
import com.xiyifen.myshop.system.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiyifen
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Map<String,Object> getGoodsList(String query, QueryRequest request) {

        Map<String,Object> resultMap=new HashMap<>();

        QueryWrapper<Goods> queryWrapper=new QueryWrapper<Goods>();
        if (StringUtils.isNotBlank(query)){
            queryWrapper.lambda().like(Goods::getGoodsName,query);
        }
        Page<Goods> page=new Page<>(request.getPagenum(),request.getPagesize());

        Page<Goods> pageInfo = goodsMapper.selectPage(page, queryWrapper);

        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("pagenum",pageInfo.getCurrent());
        resultMap.put("goods",pageInfo);
        return resultMap;
    }
}
