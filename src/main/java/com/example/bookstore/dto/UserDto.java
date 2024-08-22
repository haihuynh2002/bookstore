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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author infoh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate birthday;
    private String gender;
    private String phone;
    private String imageUrl;
    private List<Role> roles;
}
