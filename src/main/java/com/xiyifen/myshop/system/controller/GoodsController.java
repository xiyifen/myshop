package com.xiyifen.myshop.system.controller;


import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.common.result.ResponseResult;
import com.xiyifen.myshop.system.entity.Goods;
import com.xiyifen.myshop.system.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xiyifen
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping
    @ApiOperation(value = "商品列表数据")
    public ResponseResult getGoodsList(String query, QueryRequest request){

        Map<String, Object> goodsList = goodsService.getGoodsList(query, request);

        return new ResponseResult().success("获取成功",goodsList);
    }


    @PostMapping
    @ApiOperation(value = "添加商品")
    public ResponseResult addGoods(@RequestBody Goods goods){

        goodsService.save(goods);
        return new ResponseResult().success("添加成功",goods);
    }


}
