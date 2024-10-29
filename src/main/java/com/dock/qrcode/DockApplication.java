package com.dock.qrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.dock.qrcode.domain.model")
public class DockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockApplication.class, args);
	}

}
