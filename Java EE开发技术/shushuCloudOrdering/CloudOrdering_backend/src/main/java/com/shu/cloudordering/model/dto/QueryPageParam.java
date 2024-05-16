package com.shu.cloudordering.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.cloudordering.model.entity.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/5/8 14:14
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPageParam {
    private String text;

    private Page<Vendor> page;
}
