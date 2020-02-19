package com.it.wechatorder.service.impl;

import com.it.wechatorder.config.WechatAccountConfig;
import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.impl.WxMpTemplateMsgServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PushMessageImpl implements PushMessage{

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","亲，请记得收货。"),
                new WxMpTemplateData("keyword1",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword2","微信点餐"),
                new WxMpTemplateData("keyword3","￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("keyword4",orderDTO.getCreateTime()+""),
                new WxMpTemplateData("remark","欢迎再次光临！")
        );
        wxMpTemplateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (WxErrorException e){
            log.error("【微信模板消息】发送失败，{}",e);
        }
    }
}
