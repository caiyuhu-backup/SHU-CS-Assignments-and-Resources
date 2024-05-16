package com.shu.cloudordering.model.vo.last;

import com.shu.cloudordering.model.vo.last.CategoryInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/5/7 13:38
 * @description: 查询商家有哪些菜时返回的信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorInfoVo {
    private String vendorName;
    private String vendorImg;
    private String Score;
    private Integer totalSold;
    private Double averagePrice;
    private String announcement;
    private Integer inBusiness;
    private List<CategoryInfoVo> categoryInfoVoList;
}
