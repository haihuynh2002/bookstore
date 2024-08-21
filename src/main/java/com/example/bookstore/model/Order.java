/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author infoh
 */
@Entity
@Setter
@Getter
@Table(name = "\"Order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal shippingPrice;
    private byte status;
    private Date createdAt;
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderBook> orderBooks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Transient
    public BigDecimal getTotal() {
        return orderBooks.stream()
                .map(line -> line.getTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transient
    public int getQuantity() {
        return orderBooks.stream()
                .mapToInt(line -> line.getQuantity())
                .sum();
    }

    @Transient
    public Date getShippingDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return c.getTime();
    }

    public void addAddress(Address address) {
        this.address = address;
        address.getOrders().add(this);
    }

    public void addPayment(Payment payment) {
        this.payment = payment;
        payment.getOrders().add(this);
    }

    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }
}
