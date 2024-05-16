package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单项/快照
 *
 * @TableName order_item
 */
@TableName(value = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 商品ID
     */
    @TableField(value = "product_id")
    private Integer productId;


    @TableField(value = "vendor_id")
    private Integer vendorId;

    /**
     * 购买数量
     */
    @TableField(value = "buy_counts")
    private Integer buyCounts;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}