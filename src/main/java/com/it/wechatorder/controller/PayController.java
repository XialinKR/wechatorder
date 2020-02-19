package com.it.wechatorder.controller;

import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.enums.PayStatusEnum;
import com.it.wechatorder.enums.ResultEnum;
import com.it.wechatorder.exception.SellException;
import com.it.wechatorder.form.OrderForm;
import com.it.wechatorder.service.OrderService;
import com.it.wechatorder.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView pay(@RequestParam("orderId")String orderId,
                            @RequestParam("returnUrl")String returnUrl,
                            Map<String,Object> map) throws UnsupportedEncodingException {
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl", returnUrl.startsWith("http://") ? returnUrl : URLDecoder.decode(returnUrl, "utf-8"));
        orderService.paid(orderDTO);
        return new ModelAndView("pay/create",map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(String data){
        payService.notify(data);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
