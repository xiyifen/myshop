package com.xiyifen.myshop.system.controller;


import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.common.result.ResponseResult;
import com.xiyifen.myshop.system.dto.AddGoodsCateParam;
import com.xiyifen.myshop.system.entity.Category;
import com.xiyifen.myshop.system.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author xiyifen
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "查询商品分类", notes = "查询商品分类111")
    public ResponseResult getGoodCategories(Integer type, QueryRequest queryRequest) {

        Map<String, Object> categories = categoryService.getCategories(type, queryRequest);

        return new ResponseResult().success("获取成功", categories);
    }

    @PostMapping
    @ApiOperation(value = "添加商品分类")
    public ResponseResult addGoodCategories(@RequestBody AddGoodsCateParam addGoodsCateParam) {

        Category category = new Category();
        BeanCopier beanCopier = BeanCopier.create(AddGoodsCateParam.class, Category.class, false);
        beanCopier.copy(addGoodsCateParam,category,null);

        categoryService.save(category);

        return new ResponseResult().success("添加成功", category);
    }

    @GetMapping("/{cid}")
    @ApiOperation(value = "通过id查询商品分类")
    public ResponseResult getCategoryById(@PathVariable(value = "cid") Integer cid) {
        Category category = categoryService.getById(cid);

        return new ResponseResult().success("获取成功", category);
    }

    @PutMapping("/{cid}")
    @ApiOperation(value = "通过id更新分类")
    public ResponseResult updateCategory(@PathVariable(value = "cid") Integer cid,
                                        @RequestBody Category categoryParam) {
        Category category = new Category();
        category.setCatId(cid);
        category.setCatName(categoryParam.getCatName());

//        category.setCatId(cid);

        boolean b = categoryService.updateById(category);
        return new ResponseResult().success("编辑成功", category);
    }

    @DeleteMapping("{cid}")
    @ApiOperation(value = "删除商品分类")
    public ResponseResult deleteCateById(@PathVariable(value = "cid", required = true) Integer cid) {
        categoryService.removeById(cid);
        return new ResponseResult().success("删除成功", null);
    }


}
