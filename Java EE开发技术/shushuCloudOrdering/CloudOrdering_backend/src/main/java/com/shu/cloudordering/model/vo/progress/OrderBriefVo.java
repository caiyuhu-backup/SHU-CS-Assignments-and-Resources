package com.shu.cloudordering.model.vo.progress;

import com.shu.cloudordering.model.vo.progress.ProductOrderVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/5/7 18:21
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBriefVo {
    private String orderId;
    private String vendorId;
    private String vendorName;
    private Double totalAmount;
    private Integer productTotalCount;
    private List<ProductOrderVo> productOrderVoList;
}
