package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 *
 * @TableName orders
 */
@TableName(value = "orders")
@Data
public class Orders implements Serializable {
    /**
     * 订单ID 同时也是订单编号
     */
    @TableId(value = "order_id")
    private String orderId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "vendor_id")
    private Integer vendorId;

    /**
     * 收货人快照
     */
    @TableField(value = "receiver_name")
    private String receiverName;

    /**
     * 收货人手机号快照
     */
    @TableField(value = "receiver_mobile")
    private String receiverMobile;

    /**
     * 收货二维码
     */
    @TableField(value = "receiver_QR_code")
    private String receiverQrCode;

    /**
     * 订单总价格
     */
    @TableField(value = "total_amount")
    private Double totalAmount;

    /**
     * 实际支付总价格
     */
    @TableField(value = "actual_amount")
    private Integer actualAmount;

    /**
     * 订单备注
     */
    @TableField(value = "order_remark")
    private String orderRemark;

    /**
     * 订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:已完成 6:已关闭
     */
    @TableField(value = "status")
    private String status;

    /**
     * 逻辑删除状态 1: 删除 0:未删除
     */
    @TableField(value = "delete_status")
    private Integer deleteStatus;

    /**
     * 创建时间（成交时间）
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 取餐时间
     */
    @TableField(value = "meal_time")
    private Date mealTime;

    /**
     * 完成时间
     */
    @TableField(value = "finish_time")
    private Date finishTime;

    @TableField(value = "score")
    private Integer score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}