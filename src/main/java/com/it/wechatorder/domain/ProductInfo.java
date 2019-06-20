package com.it.wechatorder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.wechatorder.enums.ProductStatusEnum;
import com.it.wechatorder.uitls.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class ProductInfo implements Serializable{

    @Id
    private String productId;
    //名字
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图
    private String productIcon;
    //状态，0正常1下架
    private Integer productStatus =ProductStatusEnum.UP.getCode();
    //类目编号
    private Integer categoryType;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
