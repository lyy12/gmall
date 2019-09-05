package com.liuyuan.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.liuyuan.gmall.mapper")
public class GmallUserManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallUserManagerApplication.class, args);
	}

}
