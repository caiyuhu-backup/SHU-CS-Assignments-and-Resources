package com.shu.cloudordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.cloudordering.model.entity.Category;
import com.shu.cloudordering.service.CategoryService;
import com.shu.cloudordering.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Silence
* @description 针对表【category(商品分类)】的数据库操作Service实现
* @createDate 2022-04-02 18:40:15
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




