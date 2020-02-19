package com.it.wechatorder.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;
    @NotEmpty(message = "手机号必填")
    @Length(min = 11,max = 11,message = "手机号长度不对")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "手机号码格式不对")
    private String phone;
    @NotEmpty(message = "地址必填")
//    @Pattern(regexp = "^([\\u4E00-\\u9FA5A-Za-z0-9_\\-]+(省|市|区|县|道|路|街|号|门)){2,}$",message = "请输入正确地址,例如：天津市河东区卫国道广宁路大直沽街23号1门")
    @Pattern(regexp = "^.+(区|镇).+(路|街).+号.+号楼.+单元.+(室|户).*$|" +
            "^.+(路|街).+号.+号楼.+单元.+(室|户).*$|" +
            "^.+县.+(路|街).+号.+号楼.+单元.+(室|户).*$|" +
            "^.+县.+(镇|乡).+(路|街).+号.+号楼.+单元.+(室|户).*$|" +
            "^.+(区|镇).+小区.+号楼.+单元.+(室|户).*$|" +
            "^.+小区.+号楼.+单元.+(室|户).*$|" +
            "^.+县.+小区.+号楼.+单元.+(室|户).*$|" +
            "^.+县.+(镇|乡).+小区.+号楼.+单元.+(室|户).*$|" +
            "^.+(路|街|里).+号.+(室|户).*$|" +
            "^.+(镇|乡).+村.+(组|屯).+号.*$|" +
            "^.+(镇|乡|街).+(村|屯).+(组|号).*$",message = "请输入正确的地址")
    private String address;

    private String openid;
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
