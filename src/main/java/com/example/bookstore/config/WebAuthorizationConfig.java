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
        http.formLogin(c -> c.loginPage("/auth/login")
                .failureUrl("/auth/login?error")
                .permitAll()
        );
        
        http.logout(c -> c.logoutUrl("/auth/login?logout").permitAll());
        
        http.csrf(c -> c.disable());
                
        http.cors(c -> c.disable());
        
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }
}
