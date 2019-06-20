package com.it.wechatorder.repository;

import com.it.wechatorder.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{

    SellerInfo findByOpenid(String openid);
}
