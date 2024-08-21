/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.dto;

import com.example.bookstore.annotation.PasswordMatches;
import com.example.bookstore.annotation.UniqueEmail;
import com.example.bookstore.annotation.ValidEmail;
import com.example.bookstore.model.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author infoh
 */
@Data
@PasswordMatches
public class RegistrationDto {
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Email can not be empty")
    @ValidEmail(message = "Email is not valid")
    @UniqueEmail(message = "Email already exists")
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;
    @NotEmpty(message = "Repeat password can not be empty")
    private String repeatPassword;
}
