package com.example.apartmentlisting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration 
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
        @Bean
        // Allow CSS, listing & individual apartment pages
        // Put everything else behind login
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/apartments/delete/**").hasRole("ADMIN")
                .requestMatchers("/apartments/edit/**").hasRole("ADMIN")
                .requestMatchers("/apartments/add/**").hasRole("ADMIN")
                .requestMatchers("/apartments").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/apartments/*").permitAll()
                .anyRequest().authenticated()
            ).formLogin(formlogin -> formlogin
                .defaultSuccessUrl("/apartments", true).permitAll());
		return http.build();
	}
        
        @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
