package com.ssafy.dancy;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EnableEncryptableProperties
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
public class DancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DancyApplication.class, args);
	}
}
