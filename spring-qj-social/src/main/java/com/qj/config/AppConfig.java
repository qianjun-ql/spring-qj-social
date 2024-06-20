package com.qj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {
	
	// something wrong with the bean
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
//		http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		http
		.authorizeHttpRequests(Authorize -> Authorize
//                .requestMatchers("/api/**").permitAll()
				.requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())

		.csrf(csrf->csrf.disable());
		
		return http.build();
	}

}
