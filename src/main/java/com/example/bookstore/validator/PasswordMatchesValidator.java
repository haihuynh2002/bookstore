/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.validator;

import com.example.bookstore.annotation.PasswordMatches;
import com.example.bookstore.dto.RegistrationDto;
import com.example.bookstore.dto.UserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author infoh
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationDto> {

    @Override
    public boolean isValid(RegistrationDto registrationDto, ConstraintValidatorContext cvc) {
        return registrationDto.getPassword().equals(registrationDto.getRepeatPassword());
    }

}
