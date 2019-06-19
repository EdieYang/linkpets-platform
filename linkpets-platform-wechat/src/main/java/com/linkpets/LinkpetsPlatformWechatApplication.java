package com.linkpets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan({"com.linkpets.wechat.dao","com.linkpets.core.dao","com.linkpets.security.dao"})
public class LinkpetsPlatformWechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkpetsPlatformWechatApplication.class, args);
	}

}
