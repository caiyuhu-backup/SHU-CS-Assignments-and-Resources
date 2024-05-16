package com.shu.cloudordering.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.cloudordering.model.entity.Orders;
import lombok.Data;

/**
 * @author: Silence
 * @date: 2022/5/8 13:06
 * @description:
 */

@Data
public class OrdersPageDTO {
    private String userId;
    private String vendorId;
    private String status;
    private Page<Orders> page;
}
