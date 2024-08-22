/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.service.RoleService;
import com.example.bookstore.service.UserService;
import java.io.IOException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author infoh
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    UserService us;

    @Autowired
    RoleService rs;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public String users(Model model) {
        List<UserDto> users = us.findAll();
        model.addAttribute("users", users);
        return "admin/user";
    }

    @GetMapping("/create")
    public String create(Model model) {
        UserDto userDto = new UserDto();
        List<Role> allRoles = rs.findAll();
        model.addAttribute("user", userDto);
        model.addAttribute("allRoles", allRoles);
        return "admin/create-user";
    }

    @PostMapping("/create")
    public String create(@RequestParam("image") MultipartFile file,
            @ModelAttribute("user") UserDto userDto) throws IOException {
        String imageUrl = us.saveImage(file);
        userDto.setImageUrl(imageUrl);

        User user = modelMapper.map(userDto, User.class);
        us.create(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        UserDto userDto = us.findUserDtoById(id);
        List<Role> allRoles = rs.findAll();
        model.addAttribute("user", userDto);
        model.addAttribute("allRoles", allRoles);
        return "admin/update-user";
    }

    @PostMapping("/update")
    public String update(@RequestParam("image") MultipartFile file,
            @ModelAttribute("user") UserDto userDto) throws IOException {
        if (file != null) {
            String imageUrl = us.saveImage(file);
            userDto.setImageUrl(imageUrl);
        }
        us.update(userDto);
        return "redirect:/admin/user";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        us.deleteById(id);
        return "redirect:/admin/user";
    }

}
