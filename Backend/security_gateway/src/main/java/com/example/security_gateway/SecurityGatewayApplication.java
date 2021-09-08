package com.example.security_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
@EnableEurekaClient
public class SecurityGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityGatewayApplication.class, args);
	}
	

}
