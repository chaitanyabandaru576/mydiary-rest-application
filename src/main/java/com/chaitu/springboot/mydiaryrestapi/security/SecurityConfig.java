package com.chaitu.springboot.mydiaryrestapi.security;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsManager congigureDataSource(DataSource dataSource) {//DB settings in application.properties were provided to this object by spring 
		UserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);
		
		return userDetailsManager;
	}//we followed spring naming in DB so it directly access it
	
	//bean for authorizations
	@Bean
	public SecurityFilterChain authorizeHttpRequests(HttpSecurity http)throws Exception {
		
		http	//lambda expressions
		.authorizeHttpRequests(
				(authorize)->{
					authorize
					.requestMatchers(HttpMethod.DELETE, "./entries/**").hasAuthority("ROLE_ADMIN")//or u can use role if that you dont need to mention ROLE_
					.requestMatchers(HttpMethod.PUT, "./entries/**").hasAuthority("ROLE_MANAGER")
					.anyRequest().authenticated();
				}
				)
		.httpBasic()
		.and()
		.csrf().disable();
		
		return http.build();
	}
	
	//in memory
	/*@Bean
	public InMemoryUserDetailsManager getMethod() {
		
		UserDetails user1=User.builder().username("chaitanya").password("{noop}radhekrishn").roles("ADMIN").build();
		UserDetails user2=User.builder().username("uppu").password("{noop}uppu").roles("MANAGER").build();
		UserDetails user3=User.builder().username("veera").password("{noop}veera").roles("EMPLOYEE").build();
		
		InMemoryUserDetailsManager obj=new InMemoryUserDetailsManager(user1,user2,user3);
		
		return obj;
	}*/

}
