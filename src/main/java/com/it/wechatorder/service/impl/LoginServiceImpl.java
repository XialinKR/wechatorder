package com.it.wechatorder.service.impl;

import com.it.wechatorder.domain.SellerInfo;
import com.it.wechatorder.repository.SellerInfoRepository;
import com.it.wechatorder.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String findByPhoneAndPassword(String phone, String password,String code) {
        SellerInfo sellerInfo = sellerInfoRepository.findByPhoneAndPassword(phone,password);
        if (sellerInfo==null){
            return "账号密码有错误";
        }
        String param = (String) redisTemplate.opsForValue().get("param");
        if (!param.equals(code)){
            return "验证码有错误";
        }
        return "登录成功";
    }

    @Override
    @Transactional
    public void saveSeller(String username, String phone, String password) {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername(username);
        sellerInfo.setPhone(phone);
        sellerInfo.setPassword(password);
        sellerInfoRepository.save(sellerInfo);
    }

    @Override
    public SellerInfo findByPhone(String phone) {
        return sellerInfoRepository.findByPhone(phone);
    }

    @Override
    public void save(SellerInfo sellerInfo) {
        sellerInfoRepository.save(sellerInfo);
    }
}
