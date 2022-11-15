package io.febrihasan.gateway.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayV2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayV2ServiceApplication.class, args);
	}

}
