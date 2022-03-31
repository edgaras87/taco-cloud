package com.jon.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// prePostEnabled - enables Spring Security pre/post annotations. 
// more about this https://www.baeldung.com/spring-security-method-security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

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
        .logoutSuccessUrl("/")
    .and()
      .oauth2Login()
        .loginPage("/login");
  }
  
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  //
  // IN MEMORY AUTHENTICATION EXAMPLE
  //
  // @Override
  // protected void configure(AuthenticationManagerBuilder auth)
  //   throws Exception {
  //   auth
  //     .inMemoryAuthentication()
  //       .withUser("buzz")
  //         .password(encoder().encode("infinity"))
  //         .authorities("ROLE_USER")
  //       .and()
  //       .withUser("woody")
  //         .password(encoder().encode("bullseye"))
  //         .authorities("ROLE_USER");
  // }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());

  } 

}