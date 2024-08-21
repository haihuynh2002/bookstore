/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author infoh
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBookKey {
    
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "book_id")
    private Long bookId;
}