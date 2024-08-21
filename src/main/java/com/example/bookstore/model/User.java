/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import com.example.bookstore.dto.AddressDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author infoh
 */
@Entity
@Setter
@Getter
public class User {

    public static final int TOKEN_EXPIRATION = 16 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String phone;
    private boolean enabled = false;
    private Date createdAt;
    private Date updateAt;
    private String imageUrl;
    private String token;
    private Date expiration;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    public void addCart(Cart cart) {
        this.setCart(cart);
        cart.setUser(this);
    }

    public void addPayment(Payment payment) {
        this.getPayments().add(payment);
        payment.setUser(this);
    }

    public void removePayment(Payment payment) {
        this.getPayments().remove(payment);
    }

    public void addOrder(Order order) {
        this.getOrders().add(order);
        order.setUser(this);
    }

    public void refreshToken() {
        String token = UUID.randomUUID().toString();
        this.token = token;
        this.expiration = calculateExpiryDate();
    }

    public boolean isTokenExpired() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().after(expiration);
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, TOKEN_EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

    public void addAddress(Address address) {
        this.getAddresses().add(address);
        address.setUser(this);
    }
}
