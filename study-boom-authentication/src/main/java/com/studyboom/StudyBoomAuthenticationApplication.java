package com.studyboom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient("study-boom-authentication")
public class StudyBoomAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBoomAuthenticationApplication.class, args);
		
	}

}
