package com.studyboom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class StudyBoomGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBoomGatewayApplication.class, args);
	}

	@Bean
	public CorsFilter getCorsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.addAllowedOrigin("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	@Bean
	public ZuulFilters zuulFilters() {
		return new ZuulFilters();
	}
}
