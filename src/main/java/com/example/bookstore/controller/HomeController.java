/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.key.CartBookKey;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartBookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author infoh
 */
@Controller
public class HomeController {

    @Autowired
    CartRepository cr;

    @Autowired
    BookRepository br;
    
    
    @Autowired
    BookService bs;

    @Autowired
    CartBookRepository cbr;

    @GetMapping("/test")
    @ResponseBody
    @Transactional
    public void test() {
        System.out.println("start");
//        IntStream.range(1, 10)
//                .forEach(num -> {
//                    Cart c = new Cart();
//                    cr.save(c);
//                    Book b = new Book();
//                    br.save(b);
//
//                    CartBookKey key = new CartBookKey(c.getId(), b.getId());
//                    CartBook cb = new CartBook();
//                    cb.setId(key);
//                    cb.addCartBook(c, b);
//                });

        Cart c = cr.findById(12L).get();
        
        List<CartBook> cartBooks = new ArrayList<>(c.getCartBooks());
        for(CartBook cb: cartBooks) {
            Book b = cb.getBook();
            System.out.println(cb.getBook().getTitle());
            c.getCartBooks().remove(cb);
            cbr.delete(cb);
        }
    }
    
    @GetMapping("/")
    public String index(Model model) {
        List<BookDto> books = bs.findAll();
        model.addAttribute("books", books);
        return "app/index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }
}
