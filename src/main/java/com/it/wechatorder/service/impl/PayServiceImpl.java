package com.it.wechatorder.service.impl;

import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.enums.ResultEnum;
import com.it.wechatorder.exception.SellException;
import com.it.wechatorder.service.OrderService;
import com.it.wechatorder.service.PayService;
import com.it.wechatorder.uitls.JsonUtil;
import com.it.wechatorder.uitls.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService{

    private final static String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOpenid(orderDTO.getOrderId());
        log.info("【微信支付】request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】response={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifydata) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人==支付人）
//        PayResponse payResponse = bestPayService.asyncNotify(notifydata);
//        log.info("【微信支付】异步通知，payResponse={}",JsonUtil.toJson(payResponse));
////        查询订单
//        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
//        //查询订单是否存在
//        if (orderDTO==null){
//            log.error("c订单不存在,orderId={}",orderDTO.getOrderId());
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        //查询订单金额是否一致
//        if (!MathUtil.equal(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
//            log.error("【微信支付】异步通知,订单金额不一致，orderId={},微信通知金额={}，系统金额={}",
//                    payResponse.getOrderId(),
//                    payResponse.getOrderAmount(),
//                    orderDTO.getOrderAmount());
//            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
//        }
//        //修改订单支付状态
//        orderService.paid(orderDTO);
        return new PayResponse();
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}",JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}",JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
