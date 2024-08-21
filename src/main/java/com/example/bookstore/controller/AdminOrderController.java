/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.service.OrderService;
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
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    OrderService os;

    @GetMapping
    public String orders(Model model) {
        List<Order> orders = os.findAll();
        model.addAttribute("orders", orders);
        return "admin/order";
    }
    
    @GetMapping("/{id}")
    public String order(@PathVariable("id") Long id, Model model) {
        Order order = os.findById(id);
        model.addAttribute("order", order);
        return "admin/order-detail";
    }
//
//    @GetMapping("/update/{id}")
//    public String create(@PathVariable("id") Long id, Model model) {
//        Order order = os.findById(id);
//        return "admin/update-order";
//    }
//
//    @PostMapping("/update")
//    public String processCreate(@ModelAttribute("role") Order order) {
//        os.update(order);
//        return "redirect:/admin/order";
//    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        os.delete(id);
        return "redirect:/admin/order";
    }
}
