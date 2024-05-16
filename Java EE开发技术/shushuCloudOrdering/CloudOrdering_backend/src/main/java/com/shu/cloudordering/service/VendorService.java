package com.shu.cloudordering.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.model.dto.*;
import com.shu.cloudordering.model.entity.Vendor;
import com.shu.cloudordering.model.vo.last.CategoryInfoVo;

/**
 * @author Silence
 * @description 针对表【vendor】的数据库操作Service
 * @createDate 2022-04-02 18:40:15
 */
public interface VendorService extends IService<Vendor> {
    Result vendorList(VendorListDTO params);

    Result getProductInfoByVendorId(Integer vendorId);

    Result vendorFuzzyQuery(QueryPageParam params);

    void handleVendorQueryWrapper(QueryWrapper<Vendor> vendorQueryWrapper, String text, Integer canteenId, SortedBy sortedBy);

    Result commentList(CommentPageDTO params);

    Result updateVendorInfo(UpdateVendorInfoDTO params);

    Result addProduct(CategoryInfoVo params);

    Result updateProduct(ProductDTO params);

    Result categoryList(Integer vendorId);
}
