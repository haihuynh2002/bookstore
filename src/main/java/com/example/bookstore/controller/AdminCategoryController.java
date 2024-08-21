/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.model.Category;
import com.example.bookstore.service.CategoryService;
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
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    CategoryService cs;

    @GetMapping
    public String categories(Model model) {
        List<Category> categories = cs.findAll();
        model.addAttribute("categories", categories);
        return "admin/category";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/create-category";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category) {
        cs.create(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/update/{id}")
    public String create(@PathVariable("id") Long id, Model model) {
        Category category = cs.findById(id);
        model.addAttribute("category", category);
        return "admin/update-category";
    }

    @PostMapping("/update")
    public String processCreate(@ModelAttribute("category") Category category) {
        cs.update(category);
        return "redirect:/admin/category";
    }

    @PostMapping("/delete/{id}")
    public String create(@PathVariable("id") Long id) {
        cs.delete(id);
        return "redirect:/admin/category";
    }
}
