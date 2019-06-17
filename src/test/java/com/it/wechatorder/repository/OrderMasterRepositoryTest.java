package com.it.wechatorder.repository;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

@Slf4j
public class OrderMasterRepositoryTest extends WechatorderApplicationTests{

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("765465424621354");
        orderMaster.setBuyerOpenid("15522");
        orderMaster.setBuyerName("小Y");
        orderMaster.setBuyerAddress("大软");
        orderMaster.setBuyerPhone("15522667037");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster = repository.save(orderMaster);
        Assert.assertNotNull(null,orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderMaster> result = repository.findByBuyerOpenid("15522",pageRequest);
        System.out.println(result.getTotalElements());
    }
}