package com.it.wechatorder.service;

import com.it.wechatorder.dto.OrderDTO;

/**
 * 推送消息
 */
public interface PushMessage {

    void orderStatus(OrderDTO orderDTO);
}
