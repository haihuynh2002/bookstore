/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.CartDto;
import com.example.bookstore.exception.BookAlreadyInCartException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryBook;
import com.example.bookstore.model.key.CartBookKey;
import com.example.bookstore.model.key.CategoryBookKey;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartBookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.CategoryBookRepository;
import com.example.bookstore.repository.CategoryRepository;
import com.example.bookstore.repository.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author infoh
 */
@Service
@Transactional
public class BookService {

    private static String uploadDirectory = "src/main/resources/static/image/book/";

    @Autowired
    BookRepository br;

    @Autowired
    UserRepository ur;

    @Autowired
    CartRepository cr;

    @Autowired
    CategoryRepository cater;

    @Autowired
    CategoryBookRepository catebr;

    @Autowired
    UserService us;

    @Autowired
    CartBookRepository cbr;

    @Autowired
    ModelMapper modelMapper;

    public BookDto findById(Long id) {
        return br.findById(id)
                .map(book -> modelMapper.map(book, BookDto.class))
                .orElseThrow(
                        () -> new RuntimeException("Book not found")
                );
    }

    public void addCart(CartDto cartDto) {
        var book = br.findById(cartDto.getBookId()).orElseThrow(() -> new RuntimeException("book not found"));
        var cart = cr.findById(cartDto.getCartId()).orElseThrow(() -> new RuntimeException("cart not found"));

        cbr.findByBookIdAndCartId(book.getId(), cart.getId()).ifPresent(
                cb -> {
                    throw new BookAlreadyInCartException("Book already exist in cart");
                }
        );

        CartBookKey key = new CartBookKey(cart.getId(), book.getId());
        CartBook cb = new CartBook();
        cb.setId(key);
        System.out.println("0000000" + cartDto.getQuantity());
        cb.setQuantity(cartDto.getQuantity());
        cb.addCartBook(cart, book);
    }

    public void createBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        br.save(book);

        if (bookDto.getCategoryIds() != null) {
            bookDto.getCategoryIds()
                    .stream()
                    .forEach(id -> {
                        Category category = cater.findById(id).get();

                        CategoryBookKey key = new CategoryBookKey(id, book.getId());
                        CategoryBook cb = new CategoryBook();
                        cb.setId(key);
                        cb.addCategoryBook(category, book);
                    });
        }
    }

    public List<BookDto> findAll(Pageable pageable) {
        List<BookDto> books = br.findAll(pageable).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .map(book -> {
                    System.out.println(book.getTitle());
                    return book;
                })
                .collect(Collectors.toList());
        return books;
    }

    public List<BookDto> findAll() {
        List<BookDto> books = br.findAll().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
        return books;
    }

    public String saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
        Files.write(fileNameAndPath, file.getBytes());
        return "/image/book/" + fileName;
    }

    public void deleteById(Long id) {
        br.deleteById(id);
    }

    public void update(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        br.save(book);
    }

    public List<Book> searchByQuery(String query) {
        return br.findAllByTitleContaining(query);
    }

    public List<Book> searchByCategory(Long id, Pageable pageable) {
        return catebr.findAllByCategoryId(id, pageable).stream()
                .map(CategoryBook::getBook)
                .collect(Collectors.toList());
    }
}
