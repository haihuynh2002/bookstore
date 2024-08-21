/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import com.example.bookstore.model.key.CartBookKey;
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
@Table(name = "cart_book")
@Setter
@Getter
public class CartBook {

    @EmbeddedId
    @JsonIgnore
    private CartBookKey id = new CartBookKey();

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private Integer quantity;

    public void addCartBook(Cart cart, Book book) {
        this.cart = cart;
        this.book = book;
        cart.getCartBooks().add(this);
        book.getCartBooks().add(this);
    }

    @Transient
    public BigDecimal getTotal() {
        return book.getOurPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
