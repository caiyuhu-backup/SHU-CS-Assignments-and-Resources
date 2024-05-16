package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品评价 
 * @TableName product_comments
 */
@TableName(value ="product_comments")
@Data
public class ProductComments implements Serializable {
    /**
     * ID
     */
    @TableId(value = "comm_id")
    private Integer commId;

    /**
     * 商家id
     */
    @TableField(value = "vendor_id")
    private String vendorId;

    /**
     * 订单项(商品快照)ID 可为空
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 评论用户id 用户名须脱敏
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 打分0-5
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 评价内容
     */
    @TableField(value = "comm_content")
    private String commContent;

    /**
     * 评价晒图(JSON {img1:url1,img2:url2}  )
     */
    @TableField(value = "comm_imgs")
    private String commImgs;

    /**
     * 评价时间 可为空
     */
    @TableField(value = "comm_time")
    private Date commTime;

    /**
     * 是否显示（1:是，0:否）
     */
    @TableField(value = "is_show")
    private Integer isShow;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}