package com.it.wechatorder.repository;

import com.it.wechatorder.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId,Pageable pageable);
}
