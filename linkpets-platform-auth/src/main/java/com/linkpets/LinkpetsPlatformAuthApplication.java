package com.linkpets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.linkpets.cms.*.dao","com.linkpets.core.dao"})
public class LinkpetsPlatformAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkpetsPlatformAuthApplication.class, args);
	}

}
