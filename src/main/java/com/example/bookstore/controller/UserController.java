/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author infoh
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService us;

    @GetMapping("/profile")
    public String read(Authentication auth, Model model) {
        User user = us.findByAuthentication(auth);
        model.addAttribute("user", user);
        model.addAttribute("payments", user.getPayments());
        model.addAttribute("addresses", user.getAddresses());
        return "app/user";
    }

    @GetMapping("/update")
    public String update(Authentication auth, Model model) {
        User user = us.findByAuthentication(auth);
        UserDto userDto = us.findUserDtoById(user.getId());
        model.addAttribute("user", userDto);
        return "app/update-user";
    }

    @PostMapping("/update")
    public String update(@RequestParam("image") MultipartFile file,
            @ModelAttribute("user") UserDto userDto) throws IOException {
        if (file != null) {
            String imageUrl = us.saveImage(file);
            userDto.setImageUrl(imageUrl);
        }
        us.update(userDto);
        return "redirect:/user/profile";
    }
    
}
