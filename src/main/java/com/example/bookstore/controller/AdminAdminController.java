/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.AdminDto;
import com.example.bookstore.dto.AdminRoleDto;
import com.example.bookstore.dto.CreateAdminDto;
import com.example.bookstore.model.AdminRole;
import com.example.bookstore.model.Role;
import com.example.bookstore.service.AdminService;
import com.example.bookstore.service.RoleService;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import lombok.extern.java.Log;
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
@Log
@Controller
@RequestMapping("/admin/admin")
public class AdminAdminController {

    @Autowired
    AdminService as;

    @Autowired
    RoleService rs;
    
    @GetMapping
    public String admins(Model model) {
        List<AdminDto> admins = as.findAll();
        model.addAttribute("admins", admins);
        return "admin/admin";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Role> roles = rs.findAll();
        AdminDto adminDto = new AdminDto();
        model.addAttribute("admin", adminDto);
        model.addAttribute("roles", roles);
        return "admin/create-admin";
    }

    @PostMapping("/create")
    public String create(@RequestParam("image") MultipartFile file,
            @ModelAttribute("admin") CreateAdminDto adminDto) throws IOException {
        String imageUrl = as.saveImage(file);
        adminDto.setImageUrl(imageUrl);
        as.create(adminDto);
        return "redirect:/admin/admin";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        AdminDto adminDto = as.findAdminDtoById(id);
        model.addAttribute("admin", adminDto);
        return "admin/update-admin";
    }

    @PostMapping("/update")
    public String update(@RequestParam("image") MultipartFile file,
            @ModelAttribute("admin") AdminDto adminDto) throws IOException {
        if (file != null) {
            String imageUrl = as.saveImage(file);
            adminDto.setImageUrl(imageUrl);
        }
        as.update(adminDto);
        return "redirect:/admin/admin";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        as.deleteById(id);
        return "redirect:/admin/admin";
    }

    @GetMapping("/role/{id}")
    public String role(@PathVariable("id") Long id, Model model) {
        Set<AdminRole> roles = as.findById(id).getAdminRoles();
        model.addAttribute("roles", roles);
        return "admin/admin-role";
    }
    
//    @GetMapping("/admin/admin/role/create/{id}")
//    public String createRole(@PathVariable("id") Long id, Model model) {
//    }
    

}
