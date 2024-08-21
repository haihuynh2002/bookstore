/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.key.CartBookKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author infoh
 */
public interface CartBookRepository extends JpaRepository<CartBook, CartBookKey>{
    Optional<CartBook> findByBookIdAndCartId(Long bookId, Long cartId);
    void deleteAllByCartId(Long id);
}
