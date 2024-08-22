/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author infoh
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Autowired
    UserService us;
    
    @ModelAttribute("cartIcon")
    public Cart cart(Authentication auth) {
        if (auth == null) {
            return new Cart();
        }
        User user = us.findByAuthentication(auth);
        return user.getCart();
    }
}
