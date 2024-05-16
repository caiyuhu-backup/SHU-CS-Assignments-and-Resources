package com.shu.cloudordering.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/13 18:08
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String productName;
    private String productImg;
    private Double sellPrice;
    private Integer stock;
    private Integer productStatus;
}
