/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.Address;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderBook;
import com.example.bookstore.model.Payment;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.model.key.OrderBookKey;
import com.example.bookstore.repository.AddressRepository;
import com.example.bookstore.repository.CartBookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.OrderBookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.PaymentRepository;
import com.example.bookstore.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author infoh
 */
@Service
@Transactional
public class OrderService {
    
    @Autowired
    UserService us;
    
    @Autowired
    BookService bs;
    
    @Autowired
    OrderBookRepository obr;
    
    @Autowired
    CartBookRepository cbr;
    
    @Autowired
    OrderRepository or;
    
    @Autowired
    CartRepository cr;
    
    @Autowired
    AddressRepository ar;
    
    @Autowired
    PaymentRepository pr;
    
    @Autowired
    UserRepository ur;
    
    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        or.save(order);
        
        User user = us.findById(orderDto.getUserId());
        Address address = ar.findById(orderDto.getAddressId()).get();
        Payment payment = pr.findById(orderDto.getPaymentId()).get();
        
        order.addUser(user);
        order.addAddress(address);
        order.addPayment(payment);
        
        Cart cart = user.getCart();
        
        List<CartBook> cartBooks = new ArrayList<>(cart.getCartBooks());
        for (CartBook cb : cartBooks) {
            OrderBook ob = new OrderBook();
            Book book = cb.getBook();
            OrderBookKey key = new OrderBookKey(order.getId(), book.getId());
            ob.setId(key);
            ob.addOrderBook(order, book);
            ob.setQuantity(cb.getQuantity());
            ob.setPrice(book.getOurPrice());
            
            cart.getCartBooks().remove(cb);
            cbr.delete(cb);
        }
    }
    
    public List<Order> findAll() {
        return or.findAll();
    }
    
    public Order findById(Long id) {
        return or.findById(id).orElseThrow(
                () -> new RuntimeException("order not found")
        );
    }

    public void update(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void delete(Long id) {
        Order order = findById(id);
        or.delete(order);
    }
    
}
