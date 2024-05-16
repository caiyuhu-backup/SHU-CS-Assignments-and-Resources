package com.shu.cloudordering.model.vo.basevo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/7 13:54
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBaseVo {
    private String productName;
    private Integer productId;
    private String productImg;
    private Double sellPrice;
}
