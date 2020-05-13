package com.bridgelabz.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Bean
	public BCryptPasswordEncoder getBcryptEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
