/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.dto;

import com.example.bookstore.annotation.UniqueEmail;
import com.example.bookstore.annotation.ValidEmail;
import com.example.bookstore.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author infoh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    
    private Long id;
    @NotEmpty(message = "Email can not be empty")
    @ValidEmail(message = "Email is not valid")
    @UniqueEmail(message = "Email already exists")
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String phone;
    private String imageUrl;
    private Set<Role> roles = new HashSet<>();
}
