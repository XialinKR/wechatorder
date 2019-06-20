package com.it.wechatorder.service.impl;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.service.OrderService;
import com.it.wechatorder.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class PushMessageImplTest extends WechatorderApplicationTests{

    @Autowired
    private PushMessage pushMessage;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("15607408388089a88d5");
        pushMessage.orderStatus(orderDTO);
    }
}