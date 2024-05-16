package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 *
 * @TableName product
 */
@TableName(value = "product")
@Data
public class Product implements Serializable {
    /**
     * 商品主键id
     */
    @TableId(value = "product_id")
    private Integer productId;

    /**
     * 商品名称 商品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 分类外键id 分类id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 销量 累计销售
     */
    @TableField(value = "sold_num")
    private Integer soldNum;

    /**
     * 默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架
     */
    @TableField(value = "product_status")
    private Integer productStatus;

    /**
     * 商品内容 商品内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 库存
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 销售价格
     */
    @TableField(value = "sell_price")
    private Double sellPrice;

    /**
     *
     */
    @TableField(value = "product_img")
    private String productImg;

    @TableField(value = "score")
    private Double score;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}