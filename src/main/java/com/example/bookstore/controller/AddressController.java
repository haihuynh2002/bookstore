/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.AddressDto;
import com.example.bookstore.model.Address;
import com.example.bookstore.model.User;
import com.example.bookstore.service.AddressService;
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
@RequestMapping("/address")
public class AddressController {

    @Autowired
    UserService us;

    @Autowired
    AddressService as;
//
//    @GetMapping
//    public String address(Model model, Authentication auth) {
//        User user = us.findByAuthentication(auth);
//        Set<AddressDto> addresses = as.getAddressDtos(user);
//        model.addAttribute("addresses", addresses);
//        return "addresses";
//    }

    @GetMapping("/create")
    public String create(Model model) {
        AddressDto addressDto = new AddressDto();
        model.addAttribute("address", addressDto);
        return "app/create-address";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("address") AddressDto addressDto,
            Authentication auth, Model model) {
        User user = us.findByAuthentication(auth);
        addressDto.setUserId(user.getId());
        as.create(addressDto);
        return "redirect:/user/profile";
    }

    @GetMapping("/update/{id}")
    public String updateAddress(@PathVariable("id") Long id, Model model,
            Authentication auth) {
        AddressDto addressDto = as.findById(id);
        model.addAttribute("address", addressDto);
        return "app/update-address";
    }

    @PostMapping("/update/{id}")
    public String updateAddress(@PathVariable("id") Long id,
            @ModelAttribute("address") AddressDto addressDto, Authentication auth) {
        User user = us.findByAuthentication(auth);
        addressDto.setId(id);
        addressDto.setUserId(user.getId());
        
        as.update(addressDto);
        return "redirect:/user/profile";
    }

    @PostMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id) {
        as.delete(id);
        return "redirect:/user/profile";
    }
}
