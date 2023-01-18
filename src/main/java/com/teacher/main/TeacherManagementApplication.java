package com.teacher.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TeacherManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherManagementApplication.class, args);

		System.out.println("Teacher Application");
	}
}