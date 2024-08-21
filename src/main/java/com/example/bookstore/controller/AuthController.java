/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.RegistrationDto;
import com.example.bookstore.exception.ExpiredTokenException;
import com.example.bookstore.exception.InvalidTokenException;
import com.example.bookstore.exception.TokenException;
import com.example.bookstore.model.User;
import com.example.bookstore.service.MailService;
import com.example.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author infoh
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService us;

    @Autowired
    MailService ms;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("user", registrationDto);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") @Valid RegistrationDto registrationDto,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }
        User user = us.registerUser(registrationDto);
        ms.sendRegisterToken(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/process-token")
    public String processToken(@RequestParam(name = "token") String token) {
        us.verifiedToken(token);
        return "redirect:/auth/login";
    }
    
    @GetMapping("/refresh-token")
    public String refreshToken(@RequestParam(name = "token") String token) {
        us.refreshToken(token);
        return "redirect:/auth/login";
    }

    @ExceptionHandler({ExpiredTokenException.class, InvalidTokenException.class})
    public String handleException(TokenException e, Model model) {
        model.addAttribute("error", e.getMessage());
        model.addAttribute("token", e.getToken());
        return "token";
    }

    @ExceptionHandler({RuntimeException.class})
    public String handleException(RuntimeException e, Model model) {
        model.addAttribute("error", e.getMessage());
        model.addAttribute("user", new RegistrationDto());
        return "register";
    }
}
