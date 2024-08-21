/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author infoh
 */
@Configuration
public class WebAuthorizationConfig {
    
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.formLogin(form ->
                form.loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
        );

        http.csrf(c -> c.disable());
                
        http.cors(c -> c.disable());
        
        http.authorizeHttpRequests(c -> 
                c.anyRequest().permitAll()
//                c.requestMatchers("/home").hasRole("USER")
//                .requestMatchers("/user/**").permitAll()
//                .anyRequest().authenticated()
        );
        
        http.logout(c -> c.logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
        );
        
        return http.build();
    }
}
