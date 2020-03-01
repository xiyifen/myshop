package com.xiyifen.myshop.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyifen.myshop.common.domain.QueryRequest;
import com.xiyifen.myshop.system.entity.Category;
import com.xiyifen.myshop.system.mapper.CategoryMapper;
import com.xiyifen.myshop.system.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiyifen
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Map<String,Object> getCategories(Integer type, QueryRequest request) {
        Map<String,Object> result=new HashMap<>();
        List<Category> topCategoryList;
        Page<Category> pageInfo=null;
        // 不传type 默认查询3层分类列表
        if (type==null)
            type=3;
        if (request.getPagenum()!=0&&request.getPagesize()!=0){
            Page<Category> page = new Page<>(request.getPagenum(), request.getPagesize());
            // 查询 1级分类的数据
             pageInfo = categoryMapper.selectPage(page, new LambdaQueryWrapper<Category>().eq(Category::getCatLevel, 0));
            topCategoryList = pageInfo.getRecords();

            result.put("total",pageInfo.getTotal());
            result.put("pagenum",pageInfo.getCurrent());
            result.put("pagesize",pageInfo.getSize());
        }else {
            topCategoryList=categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getCatLevel, 0));
        }
        // 查询小于type的所有数据
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>().lt(Category::getCatLevel, type));
        // 最后返回的数据
        List<Category> resCategoryList=new ArrayList<>();

        for (Category category:categoryList){
            // 无论要查询到几层的数据，最后一层的children属性不初始化。数据库中的level是从0开始计数，因此type需-1
            if (category.getCatLevel()< (type-1)){
                category.initChildren();
            }
        }
        // // 遍历所有数据
        for (Category category:categoryList){
            //
            for (Category top:topCategoryList){
                // 如果当前category的id=1层菜单中的id相同，将category放入到resCategoryList,并结束当前循环，
                // 因为当前category就是1层分类，没有父节点
                if (category.getCatId().equals(top.getCatId())){
                    resCategoryList.add(category);
                    break;
                }
            }
            // 如果当前category不是1层分类，那么需要为其寻找父级分类。
            // 只有一个父级分类，因此找到即中断此处循环。
            for (Category parent:categoryList){
                if (category.getCatPid().equals(parent.getCatId())){
                    parent.getChildren().add(category);
                    break;
                }
            }
        }

        result.put("result",resCategoryList);
        return result;
    }
}
