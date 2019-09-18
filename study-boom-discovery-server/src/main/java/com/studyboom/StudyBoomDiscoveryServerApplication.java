package com.studyboom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StudyBoomDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBoomDiscoveryServerApplication.class, args);
	}

}
