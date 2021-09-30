package com.git.myworkspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyworkspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyworkspaceApplication.class, args);
	}

}
