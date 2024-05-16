package com.shu.cloudordering.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.model.dto.CommentDTO;
import com.shu.cloudordering.model.dto.OrdersDTO;
import com.shu.cloudordering.model.dto.OrdersPageDTO;
import com.shu.cloudordering.model.entity.Orders;
import com.shu.cloudordering.model.vo.progress.OrderBriefVo;

import java.util.List;

/**
 * @author Silence
 * @description 针对表【orders(订单 )】的数据库操作Service
 * @createDate 2022-04-02 18:40:15
 */
public interface OrdersService extends IService<Orders> {

    Result addOrder(OrdersDTO params);

    Result queryByOrderID(String orderId);

    Result getStatusByOrderID(String orderId);

    Result queryByUserId(OrdersPageDTO params);

    Page<Orders> getOrdersByUserIDAndStatus(OrdersPageDTO params);

    List<OrderBriefVo> getOrderBriefVoList(List<Orders> ordersList);

    Result commentOrder(CommentDTO params);

    Result oftenBuy(Integer userId);

    Result writeOff(String orderId);

    Result queryByVendorId(OrdersPageDTO params);
}
