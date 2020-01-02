package com.demo.springcloudmicroserviceclient2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@FeignClient("CLIENT1")
interface Client1Feign {
	@GetMapping("/client1")
	String getName();
}

@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@Configuration
@EnableFeignClients
public class SpringcloudmicroserviceClient2Application {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Client1Feign client1Feign;
	
	@Value("${test}")
	String name = "World";
	
	@GetMapping("/hello")
	public String get() {
		return name;
	}
	
	@HystrixCommand(fallbackMethod = "reliable")
	@GetMapping("/feign")
	public String feign() {
		LOG.info("client2-feign-call=================================================");
		
		return client1Feign.getName();
	}

	public String reliable() {
		return "THIS IS BACKUP CALL";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudmicroserviceClient2Application.class, args);
	}

}
