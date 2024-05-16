package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName vendor
 */
@TableName(value = "vendor")
@Data
public class Vendor implements Serializable {
    /**
     *
     */
    @TableId(value = "vendor_id", type = IdType.AUTO)
    private Integer vendorId;

    /**
     *
     */
    @TableField(value = "canteen_id")
    private Integer canteenId;

    /**
     *
     */
    @TableField(value = "vendor_name")
    private String vendorName;

    /**
     *
     */
    @TableField(value = "score")
    private Double score;

    /**
     *
     */
    @TableField(value = "in_business")
    private Integer inBusiness;

    /**
     *
     */
    @TableField(value = "total_sold")
    private Integer totalSold;

    /**
     *
     */
    @TableField(value = "average_price")
    private Double averagePrice;

    /**
     * 公告
     */
    @TableField(value = "announcement")
    private String announcement;

    @TableField(value = "vendor_img")
    private String vendorImg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}