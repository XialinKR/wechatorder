package com.it.wechatorder.service;

import com.it.wechatorder.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifydata);

    RefundResponse refund(OrderDTO orderDTO);
}
