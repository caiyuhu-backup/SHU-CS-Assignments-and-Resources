package com.shu.cloudordering.model.vo.progress;

import com.shu.cloudordering.model.vo.basevo.ProductBaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/7 13:56
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVendorVo extends ProductBaseVo {
    private Integer soldNum;
    private Integer stock;
    private Integer productStatus;
}
