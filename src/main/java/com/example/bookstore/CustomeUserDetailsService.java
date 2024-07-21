/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore;

import com.example.bookstore.model.SecurityUserDetails;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author infoh
 */
public class CustomeUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
        
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = new SecurityUserDetails(userRepository.findByUsername(username));
        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }
    
}
