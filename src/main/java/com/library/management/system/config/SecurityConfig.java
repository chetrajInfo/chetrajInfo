package com.library.management.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//if you don't use this securityconfig because of Spring Security it will not allow the h2-console to run in the localhost
//when you run the server in webpage using url http://localhost:8080/h2-console/ it will throw whitespace error status 402 forbidden
//after permitAll() for the path for /h2-console now it will allow h2 databse to run in the web host
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() // Allow H2 console access
                .anyRequest().authenticated()              // All other paths should be authenticated
                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")     // Disable CSRF for H2 console
                .and()
                .headers()
                .frameOptions().sameOrigin();             // Allow H2 console to work with frames
    }
}