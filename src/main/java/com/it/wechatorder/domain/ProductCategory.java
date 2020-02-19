package com.it.wechatorder.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
//@Table(name = "product_category")
@DynamicUpdate
public class ProductCategory implements Serializable{

    private static final long serialVersionUID = 5293431151381320330L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;

    public ProductCategory() {
    }
}
