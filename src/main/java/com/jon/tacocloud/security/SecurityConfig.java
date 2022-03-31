package com.jon.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// prePostEnabled - enables Spring Security pre/post annotations. 
// more about this https://www.baeldung.com/spring-security-method-security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
      .antMatchers("/design", "/orders").access("hasRole('USER')")
      .antMatchers("/admin").access("hasRole('ADMIN')")
      .antMatchers("/", "/**").access("permitAll")
    .and()
      .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/", true)
    .and()
      .logout()
        .logoutSuccessUrl("/");
  }
  
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }


}