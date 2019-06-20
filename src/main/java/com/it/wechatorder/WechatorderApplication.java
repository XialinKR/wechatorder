package com.it.wechatorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WechatorderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatorderApplication.class, args);
	}

}
