package com.appdev.photoapp.config.PhotoConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PhotoConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoConfigServerApplication.class, args);
	}

}
