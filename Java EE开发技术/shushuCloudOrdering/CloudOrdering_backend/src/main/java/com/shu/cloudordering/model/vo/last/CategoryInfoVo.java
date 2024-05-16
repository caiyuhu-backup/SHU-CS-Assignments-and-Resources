package com.shu.cloudordering.model.vo.last;

import com.shu.cloudordering.model.vo.progress.ProductVendorVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/5/7 13:33
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInfoVo {
    private Integer vendorId;
    private String categoryName; // 分类名称
    private List<ProductVendorVo> productVendorVoList; // 产品列表
}
