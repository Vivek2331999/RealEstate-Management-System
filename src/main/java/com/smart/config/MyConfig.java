package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
     
     @Bean
 	public DaoAuthenticationProvider getauthenticationProvider() {
 		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
 		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
 		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
 		return daoAuthenticationProvider;

 	}
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	 http.authorizeRequests().requestMatchers("/home/**").hasRole("ADMIN").requestMatchers("/user/**").hasRole("USER")
			.requestMatchers("/**").permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
			.defaultSuccessUrl("/").and().csrf().disable();
    	 http.authenticationProvider(getauthenticationProvider());
         return http.build();
     }
//////     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	 http.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**").hasRole("USER")
//			.requestMatchers("/**").permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
//			.defaultSuccessUrl("/").and().csrf().disable();
//    	 http.authenticationProvider(getauthenticationProvider());
//         return http.build();
//     }  
//     
//     
//     
//     
//     
//     
//     

}
