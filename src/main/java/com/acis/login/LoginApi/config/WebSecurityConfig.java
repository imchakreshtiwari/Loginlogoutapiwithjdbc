package com.acis.login.LoginApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	 public WebSecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
	 
	 public WebSecurityConfig() {
			super();
		
		}


	@Bean
	 public BCryptPasswordEncoder passwordEncoder()
	 {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	 }
	
	
	 	@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth)  throws Exception
	    {
	 	   auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	
	 	}
	 	
	 	@Override
	 	protected void configure(HttpSecurity http) throws Exception {
	 		
	 		
	 		http.csrf().disable();
	 		
	 		http.authorizeRequests().antMatchers("/","/login","logout").permitAll();
	 		
	 		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
	 		
	 		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
	 		
	 		//403
	 		 http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	 		 
	 		 //form data manage
	 		  http.authorizeRequests().and().formLogin()//
              // Submit URL of login page.
              .loginProcessingUrl("/j_spring_security_check") // Submit URL
              .loginPage("/login")//
              .defaultSuccessUrl("/userAccountInfo")//
              .failureUrl("/login?error=true")//
              .usernameParameter("username")//
              .passwordParameter("password")
              // Config for Logout Page
              .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	 	}
	 	
	 	
	
}
