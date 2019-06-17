package com.it.wechatorder.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it.wechatorder.domain.OrderDetail;
import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.enums.ResultEnum;
import com.it.wechatorder.exception.SellException;
import com.it.wechatorder.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List< OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
