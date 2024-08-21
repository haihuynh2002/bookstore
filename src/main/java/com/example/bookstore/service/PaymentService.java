/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.dto.PaymentDto;
import com.example.bookstore.model.Payment;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.PaymentRepository;
import com.example.bookstore.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author infoh
 */
@Service
@Transactional
public class PaymentService {

    @Autowired
    PaymentRepository pr;

    @Autowired
    UserService us;

    @Autowired
    UserRepository ur;

    @Autowired
    ModelMapper modelMapper;

    public PaymentDto findById(Long id) {
        return pr.findById(id)
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .orElseThrow(() -> new RuntimeException("payment is not found"));
    }
    
    public Set<PaymentDto> findByUser(User user) {
        return user.getPayments().stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toSet());
    }

    public void update(PaymentDto paymentDto) {
        Payment payment = pr.findById(paymentDto.getId()).get();
        modelMapper.map(paymentDto, payment);
        pr.save(payment);
    }

    public void create(PaymentDto paymentDto) {
        User user = us.findById(paymentDto.getUserId());
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        user.addPayment(payment);
        ur.save(user);
    }

    public void delete(Long id) {
        Payment payment = pr.findById(id).orElseThrow(
                () -> new RuntimeException("payment is not exists")
        );
        User user = payment.getUser();
        user.getPayments().remove(payment);
        pr.deleteById(id);
        ur.save(user);
    }
}
