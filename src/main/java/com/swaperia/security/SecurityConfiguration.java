package com.swaperia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.swaperia.security.jwt.JWTFilter;

import jakarta.annotation.security.PermitAll;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public JWTFilter authenticationJwtTokenFilter() {
		return new JWTFilter();
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.authorizeHttpRequests(authorize -> 
		authorize.requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
		 .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
		 .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
		 .requestMatchers( "/ws/**").permitAll()
		 .anyRequest().authenticated());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
