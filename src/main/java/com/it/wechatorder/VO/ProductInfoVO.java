package com.it.wechatorder.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductInfoVO implements Serializable{

    private static final long serialVersionUID = 2018739031473909686L;
    @JsonProperty("id")
    private String productId;

    //名字
    @JsonProperty("name")
    private String productName;
    //单价
    @JsonProperty("price")
    private BigDecimal productPrice;
    //描述
    @JsonProperty("description")
    private String productDescription;
    //小图
    @JsonProperty("icon")
    private String productIcon;

}
