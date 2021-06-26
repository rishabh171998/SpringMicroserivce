package com.appdev.photoapp.api.users.PhotoAppUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class PhotoAppUsersApplication {


	public static void main(String[] args) {
		SpringApplication.run(PhotoAppUsersApplication.class, args);
	}
    @Bean
	public BCryptPasswordEncoder Bcrypt()
	 {
	 	return new BCryptPasswordEncoder();
	 }
}

