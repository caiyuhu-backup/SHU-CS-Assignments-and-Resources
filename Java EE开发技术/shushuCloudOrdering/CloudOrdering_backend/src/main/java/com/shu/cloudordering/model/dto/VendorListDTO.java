package com.shu.cloudordering.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.cloudordering.model.entity.Vendor;
import lombok.Data;

/**
 * @author: Silence
 * @date: 2022/4/2 19:42
 * @description:
 */

@Data
public class VendorListDTO {
    private Integer canteenId;
    private SortedBy sortedBy; // 根据此值,确定返回具体那一页的信息
    private String text; // 模糊查询
    private Page<Vendor> page;
}
