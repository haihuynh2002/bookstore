/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.PaymentDto;
import com.example.bookstore.model.Payment;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.PaymentRepository;
import com.example.bookstore.service.PaymentService;
import com.example.bookstore.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author infoh
 */
@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    UserService us;

    @Autowired
    PaymentService ps;
//
//    @GetMapping
//    public String payment(Authentication auth, Model model) {
//        User user = us.findByAuthentication(auth);
//        Set<PaymentDto> payments = ps.findByUser(user);
//        model.addAttribute("payments", payments);
//        return "payments";
//    }

    @GetMapping("/create")
    public String create(Model model) {
        PaymentDto paymentDto = new PaymentDto();
        model.addAttribute("payment", paymentDto);
        return "app/create-payment";
    }
    
    @PostMapping("/create")
    public String create(@ModelAttribute("payment") PaymentDto paymentDto, 
            Authentication auth) {
        User user = us.findByAuthentication(auth);
        paymentDto.setUserId(user.getId());
        ps.create(paymentDto);
        return "redirect:/user/profile";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        PaymentDto paymentDto = ps.findById(id);
        model.addAttribute("payment", paymentDto);
        return "app/update-payment";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("payment") PaymentDto paymentDto, Model model) {
        ps.update(paymentDto);
        return "redirect:/user/profile";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        ps.delete(id);
        return "redirect:/user/profile#payment";
    }



}
