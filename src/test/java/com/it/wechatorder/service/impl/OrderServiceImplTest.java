package com.it.wechatorder.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.OrderDetail;
import com.it.wechatorder.domain.OrderMaster;
import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.enums.OrderStatusEnum;
import com.it.wechatorder.enums.PayStatusEnum;
import com.it.wechatorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderServiceImplTest extends WechatorderApplicationTests{

    @Autowired
    private OrderService service;

    private final String BUYER_OPENID= "456";

    private final String ORDER_ID = "1560396238748a4d45f";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("燎师兄");
        orderDTO.setBuyerAddress("大软");
        orderDTO.setBuyerPhone("15236485136");
        orderDTO.setBuyerOpenid("456");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setProductId("123456");
//        orderDetail.setProductQuantity(2);
//        orderDetailList.add(orderDetail);
        orderDetail.setProductId("1234567");
        orderDetail.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = service.create(orderDTO);
        log.info("【创建订单】 result={}",result);
    }

    @Test
    public void findOne() {
        OrderDTO result = service.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderDTO> result = service.findList(BUYER_OPENID,pageRequest);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        OrderDTO result = service.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        OrderDTO result = service.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        OrderDTO result = service.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void findList1() {
        PageRequest pageRequest = new PageRequest(0,1);
        Page page = service.findList(pageRequest);
        Assert.assertTrue("查询所有订单列表",page.getTotalElements()>0);
    }
}