package com.sid.springmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
PasswordEncoder passwordEncoder=passwordEncoder();
System.out.println("************************");
System.out.println(passwordEncoder.encode("1234"));
System.out.println("************************");
//auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");
auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1234")).roles("USER");
auth.inMemoryAuthentication().withUser("ADMIN").password(passwordEncoder.encode("1234")).roles("USER","ADMIN");

}
@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.formLogin();	
	//http.httpBasic();
	http.authorizeRequests().antMatchers("/admin/**","/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
	http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER");
	http.authorizeRequests().antMatchers("/user/**").permitAll();
	//http.authorizeRequests().anyRequest().authenticated();
	//http.csrf().disable();
	http.csrf();
	http.exceptionHandling().accessDeniedPage("/notAutorized");
}
@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
}
