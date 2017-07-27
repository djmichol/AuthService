package com.michol;

import com.michol.configuration.AuthConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AuthConfiguration.class})
public class AuthApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AuthApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AuthApplication.class, args);
	}
}