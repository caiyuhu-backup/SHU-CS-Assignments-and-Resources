package com.shu.cloudordering.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.cloudordering.model.entity.Product;
import com.shu.cloudordering.model.entity.Vendor;

/**
 * @author Silence
 * @description 针对表【product(商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表)】的数据库操作Service
 * @createDate 2022-04-02 18:40:15
 */
public interface ProductService extends IService<Product> {
    Vendor getVendorByProductId(Integer productId);

}
