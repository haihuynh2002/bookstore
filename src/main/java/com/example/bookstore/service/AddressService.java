/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.dto.AddressDto;
import com.example.bookstore.model.Address;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.AddressRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author infoh
 */
@Service
@Transactional
public class AddressService {

    @Autowired
    UserService us;

    @Autowired
    AddressRepository ar;

    @Autowired
    ModelMapper modelMapper;

    public void create(AddressDto addressDto) {
        User user = us.findById(addressDto.getUserId());
        Address address = modelMapper.map(addressDto, Address.class);
        user.addAddress(address);
    }

    public AddressDto findById(Long id) {
        return ar.findById(id)
                .map(address -> modelMapper.map(address, AddressDto.class))
                .orElseThrow(
                () -> new RuntimeException("Address is not exist")
        );
    }

    public void update(AddressDto addressDto) {
        Address address = ar.findById(addressDto.getId())
                .orElseThrow(() -> new RuntimeException("User doesn't this address"));;
        modelMapper.map(addressDto, address);
        ar.save(address);
    }

    public void delete(Long id) {
        ar.deleteById(id);
    }

    public Set<AddressDto> getAddressDtos(User user) {
        return user.getAddresses().stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toSet());
    }
}
