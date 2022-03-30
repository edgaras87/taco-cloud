package com.jon.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// prePostEnabled - enables Spring Security pre/post annotations. 
// more about this https://www.baeldung.com/spring-security-method-security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests()
                // 1. available methods
                //  requests for '/design' and '/orders' are available only to authenticated users
                .mvcMatchers("/design", "/orders").hasRole("USER")
                .mvcMatchers("/admin", "/admin/**").hasRole("ADMIN")
                
                // verify post request
                //.mvcMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                
                // all other requests permitted for all users
                .anyRequest().permitAll() // this should always in the end
            
            .and().formLogin().loginPage("/login")
                //.loginProcessingUrl("/authenticate") // for th:action="@{/authenticate} default path /login"
                //.usernameParameter("user") // default username
                //.passwordParameter("pwd") // default password
                .defaultSuccessUrl("/", true) // in successful login, landing page will be home.html,
                                                // (by Default landing page will be last url request),
                                                // true forces to land to this page whatever it takes, even if it not exists. 
            
            // log out of the application. (session will be cleared)
            .and().logout()
                .logoutSuccessUrl("/") // default redirected to the login page          
            // turn off csrf protection
            //.and().csrf().disable()

            // Make H2-Console non-secured; for debug purposes
            //.and().csrf().ignoringAntMatchers("/h2-console/**")

                // 2. using SpEL expression
                // .antMatchers("/design", "/orders").access("hasRole('USER')")
                // .antMatchers("/", "/**").access("permitAll")

                // Custom security constrain: allowing design and order tacos only on TUESDAY.
                // .antMatchers("/design", "/orders")
                // .access("hasRole('USER') && " +
                //  "T(java.util.Calendar).getInstance().get("+
                //  "T(java.util.Calendar).DAY_OF_WEEK) == " +
                //  "T(java.util.Calendar).TUESDAY")

            .and().build();

    }
}
