package com.studyboom;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

@SpringBootApplication(scanBasePackages = "com.studyboom")
@EnableDiscoveryClient
@RibbonClient("study-boom-admin")
@EnableJpaRepositories("com.studyboom")
public class StudyBoomAdminApplication {

	private static final Logger LOG = Logger
			.getLogger(StudyBoomAdminApplication.class);

	public static void main(String[] args) {
		setRandomPort(11001, 11999);
		SpringApplication.run(StudyBoomAdminApplication.class, args);
	}

	private static void setRandomPort(int min, int max) {
		try {
			String userDefinedPort = System.getProperty("server.port",
					System.getenv("SERVER_PORT"));
			if (StringUtils.isEmpty(userDefinedPort)) {
				int port = SocketUtils.findAvailableTcpPort(min, max);
				LOG.info("Server Port Set to " + port);
				System.setProperty("server.port", String.valueOf(port));
			}
		} catch (Exception e) {
			LOG.error("ERROR SETTING THE RANDOM PORT BETWEEN " + min + " AND "
					+ max + " -> " + e.getLocalizedMessage(), e);
		}
	}
}
