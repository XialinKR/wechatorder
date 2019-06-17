package com.it.wechatorder.domain;

import com.it.wechatorder.enums.OrderStatusEnum;
import com.it.wechatorder.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    @Id
    /*订单id*/
    private String orderId;
    /*买家姓名*/
    private String buyerName;
    /*买家手机号*/
    private String buyerPhone;
    /*买家地址*/
    private String buyerAddress;
    /*买家微信Openid*/
    private String buyerOpenid;
    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态，默认为新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /*支付状态，默认为0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
}
