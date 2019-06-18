package com.it.wechatorder.repository;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class OrderDetailRepositoryTest extends WechatorderApplicationTests{

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("xxxx");
        orderDetail.setOrderId("xxx");
        orderDetail.setProductIcon("http://xxxx.png");
        orderDetail.setProductId("xxx");
        orderDetail.setProductName("xxx");
        orderDetail.setProductPrice(new BigDecimal(2.0));
        orderDetail.setProductQuantity(2);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOAndOrderId() {
        List<OrderDetail> result = repository.findByOrderId("11111111");
        Assert.assertNotEquals(0,result.size());
    }
}