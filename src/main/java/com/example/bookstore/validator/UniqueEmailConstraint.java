package com.example.bookstore.validator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.example.bookstore.annotation.UniqueEmail;
import com.example.bookstore.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author infoh
 */
@Component
@AllArgsConstructor
public class UniqueEmailConstraint implements ConstraintValidator<UniqueEmail, String>{
    
    @Autowired
    private UserRepository ur;    
    
    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        return ur.findByEmail(t).isEmpty();
    }
    
}
