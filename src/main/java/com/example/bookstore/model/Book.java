/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author infoh
 */
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String language;
    private String format;
    private String publisher;
    private String publicationDate;
    private int numberofPages;
    private int isbn;
    private double shippingWeight;
    private BigDecimal listPrice;
    private BigDecimal ourPrice;
    private boolean active = true;
        private Date createdAt;
    private Date updateAt;

    @Column(columnDefinition = "text")
    private String description;
    private int inStockNumber;
    @Transient
    private MultipartFile image;
    
    @OneToMany(mappedBy = "book")
    private Set<BookCategory> bookCategories;
    
    @OneToMany(mappedBy = "book")
    private Set<OrderBook> orderBooks;
}
