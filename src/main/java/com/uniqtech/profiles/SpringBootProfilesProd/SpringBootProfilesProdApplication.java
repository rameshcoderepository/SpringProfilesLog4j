package com.uniqtech.profiles.SpringBootProfilesProd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootProfilesProdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProfilesProdApplication.class, args);

//		String dummyPassword = System.getenv("DB_PASS");
//
//		String dummyUsername = System.getProperty("DB_PASS");
//
//		System.out.println("From Environment Variable: " + dummyPassword);
//		System.out.println("From VM Arguments:" + dummyUsername);

	}

}
