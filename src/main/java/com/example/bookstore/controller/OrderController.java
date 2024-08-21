/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.Address;
import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.Payment;
import com.example.bookstore.model.User;
import com.example.bookstore.service.AddressService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.service.UserService;
import java.util.List;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author infoh
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    OrderService os;

    @Autowired
    AddressService as;

    @Autowired
    UserService us;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/create")
    public String create(Authentication auth, Model model) {
        User user = us.findByAuthentication(auth);
        OrderDto orderDto = new OrderDto();

        List<CartBook> cartBooks = user.getCart().getCartBooks();
        Set<Payment> payments = user.getPayments();
        Set<Address> addresses = user.getAddresses();

        model.addAttribute("orderBooks", cartBooks);
        model.addAttribute("payments", payments);
        model.addAttribute("addresses", addresses);
        model.addAttribute("order", orderDto);
        return "order";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("order") OrderDto orderDto, Model model,
            Authentication auth) {
        User user = us.findByAuthentication(auth);
        orderDto.setUserId(user.getId());
        os.createOrder(orderDto);
        return "redirect:/";
    }
}
