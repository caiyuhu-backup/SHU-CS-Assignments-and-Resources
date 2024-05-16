package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName canteen
 */
@TableName(value ="canteen")
@Data
public class Canteen implements Serializable {
    /**
     * 
     */
    @TableId(value = "canteen_id", type = IdType.AUTO)
    private Integer canteenId;

    /**
     * 
     */
    @TableField(value = "canteen_name")
    private String canteenName;

    /**
     * 
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 
     */
    @TableField(value = "location")
    private String location;

    /**
     * 
     */
    @TableField(value = "vendor_total_count")
    private Integer vendorTotalCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}