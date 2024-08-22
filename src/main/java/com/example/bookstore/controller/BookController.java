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
import com.example.bookstore.model.User;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CategoryService;
import com.example.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/book")
public class BookController {

    private static int PAGE_SIZE = 6;

    @Autowired
    BookService bs;

    @Autowired
    UserService us;

    @Autowired
    CategoryService cs;

    @GetMapping
    public String books(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model) {
        Pageable section = PageRequest.of(page, PAGE_SIZE);
        List<BookDto> books = bs.findAll(section);
        List<Category> categories = cs.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("books", books);
        model.addAttribute("page", ++page);
        return "app/book";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") Long id, Model model) {
        BookDto bookDto = bs.findById(id);
        CartDto cartDto = new CartDto();
        model.addAttribute("book", bookDto);
        model.addAttribute("cart", cartDto);
        return "app/book-detail";
    }

    @PostMapping("/addCart")
    public String postBook(@ModelAttribute("cart") CartDto cartDto,
            Authentication auth) {
        Long cartId = us.findByAuthentication(auth).getCart().getId();
        cartDto.setCartId(cartId);
        bs.addCart(cartDto);
        return "redirect:/book";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Book> books = bs.searchByQuery(query);
        List<Category> categories = cs.findAll();
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        return "app/book";
    }

    @GetMapping("/category")
    public String category(@RequestParam("id") Long id,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            Model model) {
        Pageable section = PageRequest.of(page, PAGE_SIZE);
        List<Book> books = bs.searchByCategory(id, section);
        List<Category> categories = cs.findAll();
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryId", id);
        model.addAttribute("page", ++page);
        return "app/category-book";
    }

    @ExceptionHandler({BookAlreadyInCartException.class})
    public String handleException(BookAlreadyInCartException e, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
