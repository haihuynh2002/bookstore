/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.dto.AddressDto;
import com.example.bookstore.dto.PaymentDto;
import com.example.bookstore.dto.RegistrationDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.ExpiredTokenException;
import com.example.bookstore.exception.InvalidTokenException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.CartBook;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author infoh
 */
@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private static String uploadDirectory = "src/main/resources/static/image/user/";
    private final UserRepository ur;
    private final RoleRepository rr;
    private final CartRepository cr;
    private final PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;

    public User findById(Long id) {
        return ur.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User is not found")
        );
    }

    public User findByEmail(String email) {
        return ur.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User is not found")
        );
    }

    public User findByAuthentication(Authentication auth) {
        return ur.findByEmail(auth.getName()).orElseThrow(
                () -> new UsernameNotFoundException("Email is not found")
        );
    }

    public User registerUser(RegistrationDto registrationDto) {
        ur.findByEmail(registrationDto.getEmail()).ifPresent(user -> {
            throw new RuntimeException("user exists");
        });
        Cart cart = new Cart();

        User user = modelMapper.map(registrationDto, User.class);
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.addCart(cart);
        return ur.save(user);
    }

    public void verifiedToken(String token) {
        User user = ur.findByToken(token).orElseThrow(
                () -> new InvalidTokenException("Wrong token", token)
        );

        if (user.isTokenExpired()) {
            throw new ExpiredTokenException("Token is expired", token);
        }

        user.setEnabled(true);
    }

    public void refreshToken(String token) {
        User user = ur.findByToken(token).orElseThrow(
                () -> new InvalidTokenException("Wrong token", token)
        );
        user.refreshToken();
    }

    public List<CartBook> findCartBooks(User user) {
        List<CartBook> cartBooks = cr.findByUserId(user.getId()).get().getCartBooks();
        return cartBooks;
    }

    public UserDto findUserDtoById(Long id) {
        return ur.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
    }

    public List<UserDto> findAll() {
        List<UserDto> users = ur.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return users;
    }

    public String saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
        Files.write(fileNameAndPath, file.getBytes());
        return "/image/user/" + fileName;
    }

    public void update(UserDto userDto) {
        User user = ur.findById(userDto.getId()).orElseThrow(
                () -> new RuntimeException("user not found")
        );
        modelMapper.map(userDto, user);
    }

    public void deleteById(Long id) {
        ur.deleteById(id);
    }

    public void create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        ur.save(user);
    }
}
