package com.appdev.photoapp.api.users.PhotoAppUsers;

import com.appdev.photoapp.api.users.PhotoAppUsers.shared.FeignErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class PhotoAppUsersApplication {


	public static void main(String[] args) {
		SpringApplication.run(PhotoAppUsersApplication.class, args);
	}
    @Bean
	public BCryptPasswordEncoder Bcrypt()
	 {
	 	return new BCryptPasswordEncoder();
	 }

	 @Bean
	 @LoadBalanced
	 public RestTemplate getRestTemplate()
	 {
	 	return new RestTemplate();
	 }

	 @Bean
	 public FeignErrorDecoder getFeignErrorDecoder()
	 {
	 	 return  new FeignErrorDecoder();
	 }
}

