package com.shu.cloudordering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.mapper.*;
import com.shu.cloudordering.model.dto.CommentDTO;
import com.shu.cloudordering.model.dto.OrdersDTO;
import com.shu.cloudordering.model.dto.OrdersPageDTO;
import com.shu.cloudordering.model.entity.*;
import com.shu.cloudordering.model.vo.last.OftenBuyVo;
import com.shu.cloudordering.model.vo.last.OrderDetailedVo;
import com.shu.cloudordering.model.vo.last.OrderStatusInfoPageVo;
import com.shu.cloudordering.model.vo.progress.OrderBriefVo;
import com.shu.cloudordering.model.vo.progress.ProductOrderVo;
import com.shu.cloudordering.service.OrdersService;
import com.shu.cloudordering.service.ProductService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Silence
 * @description 针对表【orders(订单 )】的数据库操作Service实现
 * @createDate 2022-04-02 18:40:15
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private VendorMapper vendorMapper;
    @Resource
    private ProductCommentsMapper productCommentsMapper;
    @Resource
    private ProductService productService;

    @Override
    public Result addOrder(OrdersDTO params) {
        Integer userId = params.getUserId();
        ArrayList<Integer> productIdList = params.getProductIdList();
        ArrayList<Integer> buyCountsList = params.getBuyCountsList();

        if (productIdList == null || buyCountsList == null) {
            return Result.fail("订单添加失败,商品传入为空");
        }

        // 1.校验库存
        // product表中查询库存
        for (int i = 0; i < productIdList.size(); i++) {
            Integer productId = productIdList.get(i);
            Product product = productMapper.selectById(productId);
            Integer stock = product.getStock();
            if (stock <= buyCountsList.get(i)) {
                String productName = product.getProductName();
                return Result.fail(productName + "库存不足");
            }
        }


        System.out.println("-----库存校验完成");

        Orders orders = new Orders();

        // 2.保存订单
        // 查询user信息
        Users users = usersMapper.selectById(userId);
        String realname = users.getRealname();
        String userMobile = users.getUserMobile();

        orders.setUserId(userId);

        orders.setCreateTime(new Date());

        // 待取餐状态
        orders.setStatus("1");
        orders.setReceiverName(realname);
        orders.setReceiverMobile(userMobile);
        //生成订单编号
        String orderId = UUID.randomUUID().toString().replace("-", "");
        orders.setOrderId(orderId);

        // 接收取餐码
        String receiverQrCode = params.getReceiverQrCode();
        orders.setReceiverQrCode(receiverQrCode);

        // 接受取餐时间与用户备注
        BeanUtils.copyProperties(params, orders);


        Date time = params.getMealTime();
        orders.setMealTime(time);

        // 3.修改order_item表
        for (int i = 0; i < productIdList.size(); i++) {
            Integer productId = productIdList.get(i);
            Integer count = buyCountsList.get(i);
            OrderItem orderItem = new OrderItem(orderId, productId, params.getVendorId(), count);
            orderItemMapper.insert(orderItem);
        }

        // 4.扣减库存：根据productID修改product表的stock
        double totalAmount = 0;
        for (int i = 0; i < productIdList.size(); i++) {
            Integer productId = productIdList.get(i);
            Product product = productMapper.selectById(productId);
            // 修改库存
            Integer stock = product.getStock();
            Integer count = buyCountsList.get(i);
            stock -= count;
            // 修改销量
            Integer soldNum = product.getSoldNum();
            soldNum += count;
            product.setSoldNum(soldNum);
            product.setStock(stock);
            productMapper.updateById(product);

            // 计算订单总价
            Double sellPrice = product.getSellPrice();
            totalAmount += sellPrice * count;
        }
        // 更改订单总价
        orders.setTotalAmount(totalAmount);

        // 修改order表,插入数据
        ordersMapper.insert(orders);

        // 5.更改商家订单数 订单数+1
        Integer vendorId = params.getVendorId();
        Vendor vendor = vendorMapper.selectById(vendorId);
        Integer originalVendorTotalSold = vendor.getTotalSold();

        // 6.更改商家平均价格
        Double averagePrice = vendor.getAveragePrice();
        double sum_price = averagePrice * originalVendorTotalSold;

        // 新的总销量
        int newVendorTotalSold = originalVendorTotalSold + 1;
        double newAveragePrice = (sum_price + totalAmount) / newVendorTotalSold;
        vendor.setAveragePrice(newAveragePrice);
        vendor.setTotalSold(newVendorTotalSold);

        // 更新商家
        vendorMapper.updateById(vendor);

        Date mealTime = orders.getMealTime();
        System.out.println(mealTime);

        return Result.success("订单添加成功,返回订单编号", orderId);
    }

    @Override
    public Result queryByOrderID(String orderId) {
        // 创建返回的Vo对象
        OrderDetailedVo orderDetailedVo = new OrderDetailedVo();


        QueryWrapper<OrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        List<OrderItem> orderItems = orderItemMapper.selectList(wrapper);

        List<ProductOrderVo> productOrderVos = new ArrayList<>();
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            // 设置商品Id与购买数量
            ProductOrderVo productOrderVo = new ProductOrderVo();
            BeanUtils.copyProperties(orderItem, productOrderVo);
            // 在product表中查询product详细信息
            Integer productId = orderItem.getProductId();
            Product product = productMapper.selectById(productId);
            BeanUtils.copyProperties(product, productOrderVo);
            productOrderVos.add(productOrderVo);
            // 计算产品总个数
            count += orderItem.getBuyCounts();
        }
        orderDetailedVo.setProductTotalCount(count);
        orderDetailedVo.setProductOrderVos(productOrderVos);


        // 根据订单查询点餐人信息
        Orders orders = ordersMapper.selectById(orderId);
        Users users = usersMapper.selectById(orders.getUserId());
        orderDetailedVo.setUserMobile(users.getUserMobile());
        orderDetailedVo.setUserName(users.getRealname());
        orderDetailedVo.setReceiverQRCode(orders.getReceiverQrCode());

        // 查询订单创建时间、总价等
        orderDetailedVo.setOrderTime(orders.getCreateTime());
        orderDetailedVo.setTotalAmount(orders.getTotalAmount());

        // 顾客评论,取餐时间
        BeanUtils.copyProperties(orders, orderDetailedVo);

        return Result.success("订单细节查询成功", orderDetailedVo);
    }

    @Override
    public Result getStatusByOrderID(String orderId) {
        Orders orders = ordersMapper.selectById(orderId);
        if (orders == null) {
            return Result.fail("订单号错误");
        }
        String status = orders.getStatus();

        return Result.success("订单状态查询成功", status);
    }

    @Override
    public Result queryByUserId(OrdersPageDTO params) {
        // 初始化返回对象
        OrderStatusInfoPageVo orderStatusInfoPageVo = new OrderStatusInfoPageVo();
        // 获取前端传来的数据
        Page<Orders> ordersByUserIDAndStatus = getOrdersByUserIDAndStatus(params);
        List<Orders> records = ordersByUserIDAndStatus.getRecords();

        List<OrderBriefVo> orderBriefVoList = getOrderBriefVoList(records);

        // 获取分页信息
        BeanUtils.copyProperties(ordersByUserIDAndStatus, orderStatusInfoPageVo);
        orderStatusInfoPageVo.setOrderBriefVoList(orderBriefVoList);
        return Result.success("该用户订单列表查询成功", orderStatusInfoPageVo);

    }

    @Override
    public Page<Orders> getOrdersByUserIDAndStatus(OrdersPageDTO params) {
        String status = params.getStatus();
        String userId = params.getUserId();
        String vendorId = params.getVendorId();
        Page<Orders> page = params.getPage();

        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();

        ordersQueryWrapper.eq("status", status);


        if (userId == null) {
            ordersQueryWrapper.eq("vendor_id", vendorId);
        } else if (vendorId == null) {
            ordersQueryWrapper.eq("user_id", userId);
        }

        ordersQueryWrapper.orderByDesc("create_time");

        return ordersMapper.selectPage(page, ordersQueryWrapper);
    }

    @Override
    public List<OrderBriefVo> getOrderBriefVoList(List<Orders> ordersList) {
        List<OrderBriefVo> orderBriefVoList = new ArrayList<>();
        for (Orders orders : ordersList) {
            // 初始化orderBriefVo 包含
            OrderBriefVo orderBriefVo = new OrderBriefVo();
            // 获取订单编号
            String orderId = orders.getOrderId();
            Result result = queryByOrderID(orderId);
            Object data = result.getData();
            OrderDetailedVo orderDetailedVo = (OrderDetailedVo) data;
            BeanUtils.copyProperties(orderDetailedVo, orderBriefVo);
            List<ProductOrderVo> productOrderVoList = new ArrayList<>(orderDetailedVo.getProductOrderVos());
            orderBriefVo.setProductOrderVoList(productOrderVoList);

            // 根据订单Id查询orderItem表获取vendorId
            QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
            orderItemQueryWrapper.eq("order_id", orderId);
            List<OrderItem> orderItems = orderItemMapper.selectList(orderItemQueryWrapper);

            // 查询商户信息
            Integer vendorId = orderItems.get(0).getVendorId();
            Vendor vendor = vendorMapper.selectById(vendorId);
            String vendorName = vendor.getVendorName();

            orderBriefVo.setOrderId(orderId);
            orderBriefVo.setVendorName(vendorName);
            orderBriefVo.setVendorId(String.valueOf(vendorId));

            orderBriefVoList.add(orderBriefVo);
        }
        return orderBriefVoList;
    }

    @Override
    public Result commentOrder(CommentDTO params) {
        // 获取orderId
        String orderId = params.getOrderId();
        Integer grade = params.getGrade();
        String text = params.getText();

        Orders orders = ordersMapper.selectById(orderId);
        // order编号错误
        if (orders == null)
            return Result.fail("订单号输入错误，请重新输入");

        // 根据订单Id查询orderItem表获取vendorId
        QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
        orderItemQueryWrapper.eq("order_id", orderId);
        List<OrderItem> orderItems = orderItemMapper.selectList(orderItemQueryWrapper);

        // 查询商户信息,获取商户ID
        Integer vendorId = orderItems.get(0).getVendorId();
        // 获取买家ID
        Integer userId = orders.getUserId();

        // 初始化插入的comment对象
        ProductComments productComments = new ProductComments();

        // 设置属性
        productComments.setUserId(String.valueOf(userId));
        productComments.setVendorId(String.valueOf(vendorId));
        productComments.setOrderId(orderId);
        productComments.setScore(grade);
        productComments.setCommContent(text);
        productComments.setCommTime(new Date());

        // 更新评论图片
        ArrayList<String> imgList = params.getImgList();
        String resStr = StringUtils.join(imgList, ' ');
        productComments.setCommImgs(resStr);

        // 更新product_comment表
        productCommentsMapper.insert(productComments);

        // 更新订单状态
        orders.setStatus("3");
        orders.setFinishTime(new Date());
        orders.setScore(grade);
        ordersMapper.updateById(orders);

        // 更新一个订单中所有的商品评分
        for (OrderItem orderItem : orderItems) {
            Integer productId = orderItem.getProductId();
            Integer buyCounts = orderItem.getBuyCounts();
            // 更新具体的商品评分
            updateProductScore(productId, buyCounts, grade);
        }

        // 更新商家评分
        Vendor vendor = vendorMapper.selectById(vendorId);
        // 原先的评分
        Double score = vendor.getScore();
        Integer newTotalSold = vendor.getTotalSold();
        int originalTotalSold = newTotalSold - 1;
        double newScore = ((score * originalTotalSold) + grade) / newTotalSold;
        // 更新评分
        vendor.setScore(newScore);
        vendorMapper.updateById(vendor);


        return Result.success("评分成功", null);
    }

    @Override
    public Result oftenBuy(Integer userId) {
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("user_id", userId).orderByDesc("finish_time").orderByDesc("create_time");
        List<Orders> ordersList = ordersMapper.selectList(ordersQueryWrapper);


        // 设置返回对象
        List<OftenBuyVo> oftenBuyVos = new ArrayList<>();
        for (Orders orders : ordersList) {
            String orderId = orders.getOrderId();
            List<OftenBuyVo> buyVoList = getProductByOrderId(orderId);
            oftenBuyVos.addAll(buyVoList);
        }

        // 设置Map,处理buyCounts
        LinkedHashMap<String, OftenBuyVo> stringOftenBuyVoLinkedHashMap = new LinkedHashMap<>();
        for (OftenBuyVo oftenBuyVo : oftenBuyVos) {
            String productName = oftenBuyVo.getProductName();
            if (!stringOftenBuyVoLinkedHashMap.containsKey(productName))
                stringOftenBuyVoLinkedHashMap.put(productName, oftenBuyVo);
            else {
                OftenBuyVo oftenBuyVo1 = stringOftenBuyVoLinkedHashMap.get(productName);
                Integer buyCounts = oftenBuyVo1.getBuyCount();
                Integer buyCounts1 = oftenBuyVo.getBuyCount();
                Integer newBuyCounts = buyCounts + buyCounts1;
                oftenBuyVo.setBuyCount(newBuyCounts);
                stringOftenBuyVoLinkedHashMap.put(productName, oftenBuyVo);
            }
        }

        // 设置返回值
        List<OftenBuyVo> res = new ArrayList<>(stringOftenBuyVoLinkedHashMap.values());

        // 设置按照购买个数降序返回
        res.sort(Comparator.comparing(OftenBuyVo::getBuyCount).reversed());

        return Result.success("最近常买查询成功", res);
    }

    @Override
    public Result writeOff(String orderId) {
        Orders orders = ordersMapper.selectOne(new LambdaQueryWrapper<Orders>().eq(Orders::getReceiverQrCode, orderId));
        if (orders == null) {
            return Result.fail("订单查找失败！");
        }
        if (!"1".equals(orders.getStatus())) {
            return Result.fail("订单已失效！");
        }
        orders.setStatus("2");
        ordersMapper.updateById(orders);
        return Result.success("核销成功", null);
    }

    @Override
    public Result queryByVendorId(OrdersPageDTO params) {
        // 初始化返回对象
        OrderStatusInfoPageVo orderStatusInfoPageVo = new OrderStatusInfoPageVo();
        // 获取前端传来的数据
        Page<Orders> ordersByUserIDAndStatus = getOrdersByUserIDAndStatus(params);
        List<Orders> records = ordersByUserIDAndStatus.getRecords();

        List<OrderBriefVo> orderBriefVoList = getOrderBriefVoList(records);

        // 获取分页信息
        BeanUtils.copyProperties(ordersByUserIDAndStatus, orderStatusInfoPageVo);
        orderStatusInfoPageVo.setOrderBriefVoList(orderBriefVoList);
        return Result.success("该用户订单列表查询成功", orderStatusInfoPageVo);
    }

    private List<OftenBuyVo> getProductByOrderId(String orderId) {
        QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<>();
        orderItemQueryWrapper.eq("order_id", orderId);

        List<OftenBuyVo> oftenBuyVos = new ArrayList<>();
        List<OrderItem> orderItems = orderItemMapper.selectList(orderItemQueryWrapper);


        for (OrderItem orderItem : orderItems) {
            Integer productId = orderItem.getProductId();
            Product product = productMapper.selectById(productId);
            OftenBuyVo oftenBuyVo = new OftenBuyVo();
            oftenBuyVo.setProductImg(product.getProductImg());
            oftenBuyVo.setProductName(product.getProductName());
            oftenBuyVo.setBuyCount(orderItem.getBuyCounts());
            Vendor vendor = productService.getVendorByProductId(productId);
            oftenBuyVo.setVendorId(vendor.getVendorId());
            oftenBuyVos.add(oftenBuyVo);
        }
        return oftenBuyVos;
    }

    private void updateProductScore(Integer productId, Integer buyCounts, Integer grade) {
        Product product = productMapper.selectById(productId);
        // 获取加上这一单的总数量
        Integer soldNum = product.getSoldNum();
        Double score = product.getScore();
        if (score == null)
            score = 0.0;
        // 计算新分数
        double newScore = ((soldNum - buyCounts) * score + grade * buyCounts) / soldNum;
        product.setScore(newScore);
        productMapper.updateById(product);
    }


}




