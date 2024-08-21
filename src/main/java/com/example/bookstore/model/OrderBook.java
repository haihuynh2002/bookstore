/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import com.example.bookstore.model.key.OrderBookKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author infoh
 */
@Entity
@Table(name = "order_book")
@Setter
@Getter
public class OrderBook {

    @EmbeddedId
    @JsonIgnore
    private OrderBookKey id = new OrderBookKey();

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer quantity;
    private BigDecimal price;
    
    @Transient
    public BigDecimal getTotal() {
            return price.multiply(BigDecimal.valueOf(quantity));
    }
    
    public void addOrderBook(Order order, Book book) {
        this.book = book;
        this.order = order;
        book.getOrderBooks().add(this);
        order.getOrderBooks().add(this);
    }
}
