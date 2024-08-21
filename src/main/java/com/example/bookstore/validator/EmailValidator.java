/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.validator;

import com.example.bookstore.annotation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

/**
 *
 * @author infoh
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String>{

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+](.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*";
        
    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        var pattern = Pattern.compile(EMAIL_PATTERN);
        var matcher = pattern.matcher(t);
        return matcher.matches();
    }
    
}
