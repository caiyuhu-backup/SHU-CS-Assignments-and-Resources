package com.shu.cloudordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.cloudordering.mapper.CategoryMapper;
import com.shu.cloudordering.mapper.ProductMapper;
import com.shu.cloudordering.mapper.VendorMapper;
import com.shu.cloudordering.model.entity.Category;
import com.shu.cloudordering.model.entity.Product;
import com.shu.cloudordering.model.entity.Vendor;
import com.shu.cloudordering.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Silence
 * @description 针对表【product(商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表)】的数据库操作Service实现
 * @createDate 2022-04-02 18:40:15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private VendorMapper vendorMapper;

    @Override
    public Vendor getVendorByProductId(Integer productId) {
        Product product = productMapper.selectById(productId);
        Integer categoryId = product.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        Integer vendorId = category.getVendorId();
        return vendorMapper.selectById(vendorId);
    }
}




