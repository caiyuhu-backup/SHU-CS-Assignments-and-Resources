package com.shu.cloudordering.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/8 18:54
 * @description: 查询商家时的排序参数
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortedBy {
    private Integer byPrice;
    private Integer byScore;
    private Integer byTotalSold;
}
