package com.demo.springcloudmicroserviceclient1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class SpringcloudmicroserviceClient1Application {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/client1")
	public String client1() {
		LOG.info("client1-log=================================================");
		return "client1";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudmicroserviceClient1Application.class, args);
	}

}
