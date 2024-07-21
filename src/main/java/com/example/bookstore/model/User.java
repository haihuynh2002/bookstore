/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

/**
 *
 * @author infoh
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User's name cannot be empty")
    @UniqueElements
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;

    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private boolean active;
    private Date createdAt;
    private Date updateAt;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Cart> carts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Payment> payments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Column(nullable = true)
    private Set<Address> addresses;
}
