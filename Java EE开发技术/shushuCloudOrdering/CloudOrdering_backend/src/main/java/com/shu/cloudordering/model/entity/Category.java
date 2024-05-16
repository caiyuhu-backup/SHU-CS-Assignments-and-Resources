package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品分类
 * @TableName category
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * 主键 分类id主键
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类名称 分类名称
     */
    @TableField(value = "category_name")
    private String categoryName;

    /**
     * 商户ID
     */
    @TableField(value = "vendor_id")
    private Integer vendorId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}