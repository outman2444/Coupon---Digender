package com.couponDigender.goods.vc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.couponDigender.**")
@MapperScan(basePackages = "com.couponDigender.**")
@EnableAutoConfiguration
@EnableSwagger2
@PropertySource("classpath:application-goods.properties")
@PropertySource("classpath:application-pdd.properties")
public class GoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsApplication.class, args);
	}

}
