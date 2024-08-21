/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.repository;

import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.OrderBook;
import com.example.bookstore.model.key.CartBookKey;
import com.example.bookstore.model.key.OrderBookKey;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author infoh
 */
public interface OrderBookRepository extends JpaRepository<OrderBook, OrderBookKey>{
}
