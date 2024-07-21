/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author infoh
 */
@Embeddable
@Data
public class BookCategoryKey implements Serializable {
    
    @Column(name = "book_id")
    private Long bookId;
    
    @Column(name = "category_id")
    private Long categoryId;
    
}
