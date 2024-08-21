/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.Address;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.Payment;
import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author infoh
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    UserService us;
    
    @GetMapping
    public String cart(Model model, Authentication auth) {
        User user = us.findByAuthentication(auth);
        Cart cart = user.getCart();
        Set<Payment> payments = user.getPayments();
        Set<Address> addresses = user.getAddresses();
        OrderDto orderDto = new OrderDto();
        model.addAttribute("cart", cart);
        model.addAttribute("payments", payments);
        model.addAttribute("addresses", addresses);
        model.addAttribute("order", orderDto);
        return "app/cart";
    }
}
