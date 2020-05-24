package com.caueruleum.pshop.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// use jdbc authentication
		
		auth.jdbcAuthentication()
		.dataSource(securityDataSource);
			
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
		.antMatchers("/",
				"/category/**", "/search/**", "/product/**",
				"/auth/register", "/auth/processRegister", 
				"/auth/registration-confirm/**"
				)
				.permitAll()
				
		.antMatchers(
				"/auth/resend-verification", "/auth/process-resend-verification", 
				"/auth/login", "/auth/processLogin", "/auth/reset-password",
				"/auth/process-reset-password","/auth/reset-password-confirmation",
				"/auth/process-reset-password-confirmation").anonymous()
		//.antMatchers("/actuator/**").hasRole("ADMIN")
		.antMatchers("/admin/**", "/actuator/**").hasRole("USER") // for now
		.antMatchers("/auth/logout").authenticated()
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/auth/login")
			.loginProcessingUrl("/auth/processLogin")
			.defaultSuccessUrl("/")
		.and()
		.logout()
			.logoutUrl("/auth/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
		.and()
			;
	}
	
	@Bean
	public PasswordEncoder encoder() 
	{
		return new BCryptPasswordEncoder();
	}
	

}
