package com.jon.tacocloud.security;

// 1. for In-memory user details service
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;

// 2.
import com.jon.tacocloud.User;
import com.jon.tacocloud.data.UserRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// prePostEnabled - enables Spring Security pre/post annotations.
// more about this https://www.baeldung.com/spring-security-method-security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 1. In-memory user details service. (does not work together with 2. , some changes must be done)
    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "buzz", encoder.encode("pass"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        usersList.add(new User(
                "woody", encoder.encode("pass"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return new InMemoryUserDetailsManager(usersList);
    }*/

    // 2.
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {


        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }/**/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                // 1. available methods
                // requests for '/design' and '/orders' are available only to authenticated
                // users
                .mvcMatchers("/design", "/orders").hasRole("USER")
                .mvcMatchers("/admin", "/admin/**").hasRole("ADMIN")

                // verify post request
                // .mvcMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")

                // all other requests permitted for all users
                .anyRequest().permitAll() // this should always in the end

                .and().formLogin().loginPage("/login")
                // .loginProcessingUrl("/authenticate") // for th:action="@{/authenticate}
                // default path /login"
                // .usernameParameter("user") // default username
                // .passwordParameter("pwd") // default password
                .defaultSuccessUrl("/", true) // in successful login, landing page will be home.html,
                                              // (by Default landing page will be last url request),
                                              // true forces to land to this page whatever it takes, even if it not
                                              // exists.
                .and().oauth2Login().loginPage("/login")
                // log out of the application. (session will be cleared)
                .and().logout()
                .logoutSuccessUrl("/") // default redirected to the login page
                // turn off csrf protection
                // .and().csrf().disable()

                // Make H2-Console non-secured; for debug purposes
                // .and().csrf().ignoringAntMatchers("/h2-console/**")

                // 2. using SpEL expression
                // .antMatchers("/design", "/orders").access("hasRole('USER')")
                // .antMatchers("/", "/**").access("permitAll")

                // Custom security constrain: allowing design and order tacos only on TUESDAY.
                // .antMatchers("/design", "/orders")
                // .access("hasRole('USER') && " +
                // "T(java.util.Calendar).getInstance().get("+
                // "T(java.util.Calendar).DAY_OF_WEEK) == " +
                // "T(java.util.Calendar).TUESDAY")

                .and().build();

    }


}
