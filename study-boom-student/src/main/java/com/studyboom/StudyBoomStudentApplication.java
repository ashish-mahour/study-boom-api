package com.studyboom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient("study-boom-student")
public class StudyBoomStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBoomStudentApplication.class, args);
	}

}
