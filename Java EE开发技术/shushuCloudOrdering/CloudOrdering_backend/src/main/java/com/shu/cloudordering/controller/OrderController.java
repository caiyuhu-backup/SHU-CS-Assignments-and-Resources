package com.shu.cloudordering.controller;

import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.model.dto.CommentDTO;
import com.shu.cloudordering.model.dto.OrdersDTO;
import com.shu.cloudordering.model.dto.OrdersPageDTO;
import com.shu.cloudordering.model.entity.Orders;
import com.shu.cloudordering.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/5/6 10:17
 * @description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrdersService ordersService;

    @PostMapping("/add")
    public Result add(@RequestBody OrdersDTO params) {
        return ordersService.addOrder(params);
    }

    @GetMapping("/query/{orderId}")
    public Result queryByOrderID(@PathVariable String orderId) {
        return ordersService.queryByOrderID(orderId);
    }

    @PostMapping("/queryByUId")
    public Result queryByUserId(@RequestBody OrdersPageDTO params) {
        return ordersService.queryByUserId(params);
    }

    @GetMapping("/status/{orderId}")
    public Result getStatusByOrderID(@PathVariable String orderId) {
        return ordersService.getStatusByOrderID(orderId);
    }

    @PostMapping("/comment")
    public Result gradeOrder(@RequestBody CommentDTO params) {
        return ordersService.commentOrder(params);
    }

    @GetMapping("/oftenBuy/{userId}")
    public Result oftenBuy(@PathVariable Integer userId) {
        return ordersService.oftenBuy(userId);
    }

    @PostMapping("writeOff/{orderId}")
    private Result writeOff(@PathVariable String orderId) {
        return ordersService.writeOff(orderId);
    }

    @PostMapping("/queryByVendorId")
    public Result queryByVendorId(@RequestBody OrdersPageDTO params) {
        return ordersService.queryByVendorId(params);
    }

    @GetMapping("/list")
    public Result orderList(){
        List<Orders> ordersList = ordersService.list();
        return Result.success("餐厅List查询成功",ordersList);
    }


}
