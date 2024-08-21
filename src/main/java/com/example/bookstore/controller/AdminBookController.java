/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.CartDto;
import com.example.bookstore.exception.BookAlreadyInCartException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CategoryService;
import com.example.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/admin/book")
public class AdminBookController {

    @Autowired
    BookService bs;

    @Autowired
    CategoryService cs;

    @GetMapping
    public String books(Model model) {
        List<BookDto> books = bs.findAll();
        model.addAttribute("books", books);
        return "admin/book";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = cs.findAll();
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        model.addAttribute("categories", categories);
        return "admin/create-book";
    }

    @PostMapping("/create")
    public String create(@RequestParam("image") MultipartFile file,
            @ModelAttribute("book") BookDto bookDto) throws IOException {
        String imageUrl = bs.saveImage(file);
        bookDto.setImageUrl(imageUrl);
        bs.createBook(bookDto);
        return "redirect:/admin/book";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BookDto bookDto = bs.findById(id);
        model.addAttribute("book", bookDto);
        return "admin/update-book";
    }

    @PostMapping("/update")
    public String update(@RequestParam("image") MultipartFile file,
            @ModelAttribute("book") BookDto bookDto) throws IOException {
        if (file != null) {
            String imageUrl = bs.saveImage(file);
            bookDto.setImageUrl(imageUrl);
        }
        bs.update(bookDto);
        return "redirect:/admin/book";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        bs.deleteById(id);
        return "redirect:/admin/book";
    }

}
