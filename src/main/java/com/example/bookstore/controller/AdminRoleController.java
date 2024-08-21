/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.model.Role;
import com.example.bookstore.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Autowired
    RoleService rs;

    @GetMapping
    public String roles(Model model) {
        List<Role> roles = rs.findAll();
        model.addAttribute("roles", roles);
        return "admin/role";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "admin/create-role";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("role") Role role) {
        rs.create(role);
        return "redirect:/admin/role";
    }

    @GetMapping("/update/{id}")
    public String create(@PathVariable("id") Long id, Model model) {
        Role role = rs.findById(id);
        model.addAttribute("role", role);
        return "admin/update-role";
    }

    @PostMapping("/update")
    public String processCreate(@ModelAttribute("role") Role role) {
        rs.update(role);
        return "redirect:/admin/role";
    }

    @PostMapping("/delete/{id}")
    public String create(@PathVariable("id") Long id) {
        rs.delete(id);
        return "redirect:/admin/role";
    }
}
