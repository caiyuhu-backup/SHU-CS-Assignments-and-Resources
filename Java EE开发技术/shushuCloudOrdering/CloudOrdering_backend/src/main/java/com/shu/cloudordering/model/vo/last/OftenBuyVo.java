package com.shu.cloudordering.model.vo.last;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/9 23:28
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OftenBuyVo {
    private String productImg;
    private String productName;
    private Integer buyCount;
    private Integer vendorId;
}
