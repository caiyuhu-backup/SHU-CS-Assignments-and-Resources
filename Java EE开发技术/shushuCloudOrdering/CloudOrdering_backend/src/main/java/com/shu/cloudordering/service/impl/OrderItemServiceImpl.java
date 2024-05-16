package com.shu.cloudordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.cloudordering.model.entity.OrderItem;
import com.shu.cloudordering.service.OrderItemService;
import com.shu.cloudordering.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author Silence
* @description 针对表【order_item(订单项/快照 )】的数据库操作Service实现
* @createDate 2022-04-02 18:40:15
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




