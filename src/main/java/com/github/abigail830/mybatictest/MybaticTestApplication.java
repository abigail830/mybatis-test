package com.github.abigail830.mybatictest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan
@SpringBootApplication
@MapperScan("com.github.abigail830.mybatictest.infrastructure.mapper")
public class MybaticTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybaticTestApplication.class, args);
	}

}
