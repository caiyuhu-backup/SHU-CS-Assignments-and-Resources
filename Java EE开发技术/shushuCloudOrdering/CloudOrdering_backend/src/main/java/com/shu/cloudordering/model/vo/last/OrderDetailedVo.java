package com.shu.cloudordering.model.vo.last;

import com.shu.cloudordering.model.vo.progress.ProductOrderVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/5/6 15:49
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailedVo {
    private String userName;
    private String userMobile;
    private String receiverQRCode;
    private Double totalAmount;
    private Integer productTotalCount;
    private String orderRemark;
    private Date orderTime;
    private Date mealTime;
    private List<ProductOrderVo> productOrderVos;
}
