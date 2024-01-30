package com.ssafy.dancy;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class DancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DancyApplication.class, args);
	}

}
