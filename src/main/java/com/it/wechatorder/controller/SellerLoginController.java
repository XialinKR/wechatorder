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
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.omg.CORBA.TIMEOUT;
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
public class SellerLoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("redisTemplate")
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
        map.put("phone",phone);
        map.put("password",password);
        if ("账号密码有错误".equals(message)){
            map.put("code",code);
            return new ModelAndView("login/login");
        }else if ("验证码有错误".equals(message)){
            map.put("code",code);
            return new ModelAndView("login/login");
        }
        //2.设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),phone,expire,TimeUnit.SECONDS);
        //3.设置token到cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,expire);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
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

    @GetMapping("/create")
    public ModelAndView createOrUpdate(@RequestParam(value = "sign",defaultValue = "0") String sign,
                                       Map<String,Object> map){
        map.put("sign",sign);
        return new ModelAndView("login/registered");
    }
    @PostMapping("/registered")
    public ModelAndView registered(String username,String phone,String password,
                                   String code,Map<String,Object> map){
        SellerInfo sellerInfo = loginService.findByPhone(phone);
        if (StringUtil.haslength(username)&&StringUtil.haslength(password)&&StringUtil.haslength(code)&&StringUtil.haslength(phone)){
            if (sellerInfo!=null){
                map.put("msg","亲，该手机号已经注册了呦");
                map.put("url","/sell/seller/login/create");
                return new ModelAndView("common/error",map);
            }
            String sendCode = (String) redisTemplate.opsForValue().get("param");
            if (!code.equals(sendCode)){
                map.put("msg","亲，验证码输错了呦");
                map.put("url","/sell/seller/login/create");
                return new ModelAndView("common/error",map);
            }
        }else {
            map.put("sign","0");
            return new ModelAndView("login/registered");
        }
        loginService.saveSeller(username,phone,password);
        map.put("url","/sell/seller/login/login");
        return new ModelAndView("common/success",map);
    }

    @PostMapping("/forget")
    public ModelAndView forgetPassword(String username,String phone,String password,
                                       String code,Map<String,Object> map){
        SellerInfo sellerInfo = loginService.findByPhone(phone);
        if (StringUtil.haslength(username)&&StringUtil.haslength(password)&&StringUtil.haslength(code)&&StringUtil.haslength(phone)){
            if (sellerInfo!=null){
                if (!sellerInfo.getUsername().equals(username)){
                    map.put("msg","亲，名称输错了呦，再想想吧");
                    map.put("url","/sell/seller/login/create?sign=1");
                    return new ModelAndView("common/error",map);
                }
                if (!sellerInfo.getPhone().equals(phone)){
                    map.put("msg","亲，手机号输错了呦，再想想吧");
                    map.put("url","/sell/seller/login/create?sign=1");
                    return new ModelAndView("common/error",map);
                }
            }else {
                map.put("msg","亲，该手机号不存在呦");
                map.put("url","/sell/seller/login/create?sign=1");
                return new ModelAndView("common/error",map);
            }
            String sendCode = (String) redisTemplate.opsForValue().get("param");
            if (!code.equals(sendCode)){
                map.put("msg","亲，验证码输错了呦");
                map.put("url","/sell/seller/login/create?sign=1");
                return new ModelAndView("common/error",map);
            }
        }else {
            map.put("sign","1");
            return new ModelAndView("login/registered");
        }
        sellerInfo.setPassword(password);
        loginService.save(sellerInfo);
        map.put("url","/sell/seller/login/login");
        return new ModelAndView("common/success",map);
    }
}
