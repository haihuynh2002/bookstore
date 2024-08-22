/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author infoh
 */
@Configuration
@Order(2)
public class AdminSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form
                -> form.loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin", false)
        );

        http.authorizeHttpRequests(c
                -> c.requestMatchers("/admin/login").permitAll()
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll()
        );

        http.csrf(c -> c.disable());

        http.cors(c -> c.disable());

        http.logout(c -> c.logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
        );

        return http.build();
    }
}
