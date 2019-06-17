package com.it.wechatorder.service.impl;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.service.OrderService;
import com.it.wechatorder.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class PayServiceImplTest extends WechatorderApplicationTests{

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1560396238748a4d45f");
        payService.create(orderDTO);
    }
}