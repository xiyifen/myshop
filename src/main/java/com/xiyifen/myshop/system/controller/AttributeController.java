package com.xiyifen.myshop.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiyifen.myshop.common.result.ResponseResult;
import com.xiyifen.myshop.system.dto.AttributeParam;
import com.xiyifen.myshop.system.entity.Attribute;
import com.xiyifen.myshop.system.service.AttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiyifen
 */
@RestController
@RequestMapping
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @GetMapping("categories/{catId}/attributes")
    @ApiOperation(value = "获取参数列表")
    public ResponseResult getCateAttrList(@PathVariable(value = "catId") Integer catId,
                                          @RequestParam(value = "attrSel", required = true) String attrSel) {
        List<Attribute> attributeList = attributeService.getBaseMapper()
                .selectList(new LambdaQueryWrapper<Attribute>()
                        .eq(Attribute::getCatId, catId)
                        .eq(Attribute::getAttrSel, attrSel)
                );
        return new ResponseResult().success("查询成功", attributeList);
    }

    @PostMapping("categories/{catId}/attributes")
    @ApiOperation(value = "添加动态参数或静态属性")
    public ResponseResult addCateAttr(@PathVariable(value = "catId") Integer catId, @RequestBody AttributeParam attributeParam) {

        Attribute attribute = new Attribute();
        attribute.setCatId(catId);

        BeanCopier beanCopier = BeanCopier.create(AttributeParam.class, Attribute.class, false);
        beanCopier.copy(attributeParam, attribute, null);

        attributeService.save(attribute);
        return new ResponseResult().success("添加成功", attribute);
    }

    @DeleteMapping("categories/{catId}/attributes/{attrId}")
    @ApiOperation(value = "删除参数")
    public ResponseResult deleteAttr(@PathVariable(value = "catId", required = true) Integer catId,
                                     @PathVariable(value = "attrId", required = true) Integer attrId
    ) {

        attributeService.remove(new LambdaQueryWrapper<Attribute>()
                .eq(Attribute::getCatId, catId)
                .eq(Attribute::getAttrId, attrId)
        );

        return new ResponseResult().success("删除成功", null);
    }

    @GetMapping("categories/{catId}/attributes/{attrId}")
    @ApiOperation(value = "根据id查询参数")
    public ResponseResult getAttrByattrId(@PathVariable(value = "catId", required = true) Integer catId,
                                          @PathVariable(value = "attrId", required = true) Integer attrId,
                                          @RequestParam(value = "attrSel", required = true) String attrSel
    ) {

        Attribute attribute = attributeService.getBaseMapper()
                .selectOne(new LambdaQueryWrapper<Attribute>()
                        .eq(Attribute::getCatId, catId)
                        .eq(Attribute::getAttrId, attrId)
                        .eq(Attribute::getAttrSel, attrSel));

        return new ResponseResult().success("获取成功",attribute);
    }

    @PutMapping("categories/{catId}/attributes/{attrId}")
    public ResponseResult saveAttr(@PathVariable(value = "catId", required = true) Integer catId,
                                   @PathVariable(value = "attrId", required = true) Integer attrId,
                                   @RequestBody AttributeParam attributeParam){
        Attribute attribute = new Attribute();
        attribute.setCatId(catId);
        attribute.setAttrId(attrId);
        BeanCopier beanCopier = BeanCopier.create(AttributeParam.class, Attribute.class, false);
        beanCopier.copy(attributeParam, attribute, null);

        attributeService.updateById(attribute);

        return new ResponseResult().success("编辑成功",attribute);

    }

}
