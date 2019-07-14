package com.eksad.latihanrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eksad.latihanrest.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiq extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(HttpSecurity htttp) throws Exception{
		htttp.httpBasic().and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
			.antMatchers("/user/**").hasAnyAuthority("ADMIN","USER")
			.and().formLogin().permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		
		auth.userDetailsService(userService).passwordEncoder(encoder());
	}

	@Bean
	public PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
}
