package model;

import java.util.Date;

/**
 * @author: Silence
 * @date: 2022/3/30 13:34
 * @description:
 */
public class shopRecords {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer number;
    private Date shopDate;

    public shopRecords() {
    }

    public shopRecords(Integer id, Integer userId, Integer productId, Integer number, Date shopDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.number = number;
        this.shopDate = shopDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getShopDate() {
        return shopDate;
    }

    public void setShopDate(Date shopDate) {
        this.shopDate = shopDate;
    }
}
