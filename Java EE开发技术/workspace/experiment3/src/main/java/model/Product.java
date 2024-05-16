package model;

import java.sql.Date;

/**
 * @author: Silence
 * @date: 2022/3/30 13:31
 * @description:
 */
public class Product {
    private Integer id;
    private Integer productCode;
    private String productName;

    public Product() {
    }

    public Product(Integer id, Integer productCode, String productName) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
