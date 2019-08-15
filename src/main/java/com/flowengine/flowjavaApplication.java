package com.flowengine;

import com.flowengine.shared.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class flowjavaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(flowjavaApplication.class, args);
	}

}
