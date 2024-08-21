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
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookKey {
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name = "book_id")
    private Long bookId;
}
