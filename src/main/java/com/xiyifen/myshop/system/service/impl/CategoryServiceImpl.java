package com.xiyifen.myshop.system.service.impl;

import com.xiyifen.myshop.system.entity.Category;
import com.xiyifen.myshop.system.mapper.CategoryMapper;
import com.xiyifen.myshop.system.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiyifen
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
