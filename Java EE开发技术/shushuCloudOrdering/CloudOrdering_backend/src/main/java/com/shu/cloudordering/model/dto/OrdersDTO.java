package com.shu.cloudordering.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author: Silence
 * @date: 2022/5/6 10:25
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private Integer userId;
    private Integer vendorId;
    private String receiverQrCode;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:MM", timezone = "GMT+8")
    private Date mealTime; // 取餐时间
    private String orderRemark; // 顾客备注
    private ArrayList<Integer> productIdList;
    private ArrayList<Integer> buyCountsList;
}
