package com.shu.cloudordering.model.vo.progress;

import com.shu.cloudordering.model.vo.basevo.ProductBaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/6 15:52
 * @description: 订单查询时，每一件商品的详细信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderVo extends ProductBaseVo {
    private Integer buyCounts;
}
