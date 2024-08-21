/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.exception;

/**
 *
 * @author infoh
 */
public class BookAlreadyInCartException extends RuntimeException {

    public BookAlreadyInCartException(String message) {
        super(message);
    }
    
    
}
