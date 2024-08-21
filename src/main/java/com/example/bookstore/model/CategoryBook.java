/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import com.example.bookstore.model.key.CartBookKey;
import com.example.bookstore.model.key.CategoryBookKey;
import com.example.bookstore.model.key.OrderBookKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author infoh
 */
@Entity
@Table(name = "category_book")
@Setter
@Getter
public class CategoryBook {

    @EmbeddedId
    @JsonIgnore
    private CategoryBookKey id = new CategoryBookKey();

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    public void addCategoryBook(Category category, Book book) {
        this.category = category;
        this.book = book;
        category.getCategoryBooks().add(this);
        book.getCategoryBooks().add(this);
    }
}