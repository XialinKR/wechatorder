package com.it.wechatorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.it.wechatorder.constant.CookieConstant;
import com.it.wechatorder.constant.RedisConstant;
import com.it.wechatorder.domain.SellerInfo;
import com.it.wechatorder.service.LoginService;
import com.it.wechatorder.uitls.CookieUtil;
import com.it.wechatorder.uitls.KeyUtil;
import com.it.wechatorder.uitls.StringUtil;
import com.lly835.bestpay.rest.type.Post;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
//import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/seller/login")
@Slf4j
public class SellerLoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoginService loginService;

    @PostMapping("/send")
    public String send(String phone){
        System.out.println(1);
        String url = "https://open.ucpaas.com/ol/sms/sendsms";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid","03865b40d83e91e5fd52741c70a5ccae");
        jsonObject.put("token","f019bf66ccd05e71d617d60cbefde363");
        jsonObject.put("appid","3fc3b7261c324dbba849aa93e014c903");
        jsonObject.put("templateid","478744");
        String param = String.valueOf(UUID.randomUUID()).substring(0,4);
        System.out.println(param);
        redisTemplate.opsForValue().set("param",param);
        redisTemplate.expire("param",60, TimeUnit.SECONDS);
        jsonObject.put("param", param +",1");
        jsonObject.put("mobile",phone);
        jsonObject.put("uid","");
        String json = JSONObject.toJSONString(jsonObject);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> httpEntity = new HttpEntity<String>(json, headers);
        String result = restTemplate.postForObject(url, httpEntity, String.class);
        log.info(phone);
        log.info(result);
        return result+param;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login/login");
    }

    @PostMapping("/vali")
    public ModelAndView vali(String phone, String password, String code,
                             Map<String,Object> map, HttpServletResponse response){
        //1.检验
        String message = loginService.findByPhoneAndPassword(phone,password,code);
        if ("账号密码有错误".equals(message)){
            map.put("msg","账号密码有误");
            map.put("url","/sell/seller/login/login");
            return new ModelAndView("common/success",map);
        }else if ("验证码有错误".equals(message)){
            map.put("msg","验证码有误");
            map.put("url","/sell/seller/login/login");
            return new ModelAndView("common/success",map);
        }
        //2.设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),phone,expire,TimeUnit.SECONDS);
        //3.设置token到cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,expire);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
        //1.查询cookie
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie!=null){
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3.清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN,null,0);
        }
        return new ModelAndView("login/login");
    }
}
