/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author infoh
 */
@Data
public class BookDto {
    private Long id;
    private Long userId;
    private String title;
    private String author;
    private String language;
    private String format;
    private String publisher;
    private String publicationDate;
    private int numberofPages;
    private int isbn;
    private double shippingWeight;
    private BigDecimal ourPrice;
    private String description;
    private int inStockNumber;
    private String imageUrl;
    private List<Long> categoryIds;
}
