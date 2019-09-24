package com.couponDigender.promotion.vc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.couponDigender.**")
@MapperScan(basePackages = "com.couponDigender.**")
@EnableAutoConfiguration
@EnableSwagger2
@PropertySource("classpath:application-promotion.properties")
@PropertySource("classpath:application-pdd.properties")
@EnableEurekaServer
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.couponDigender.**")
public class PromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromotionApplication.class, args);
	}

}
