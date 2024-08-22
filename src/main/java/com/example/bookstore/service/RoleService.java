/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.model.Role;
import com.example.bookstore.repository.RoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class RoleService {

    @Autowired
    RoleRepository rr;

    @Autowired
    ModelMapper modelMapper;

    public List<Role> findAll() {
        return rr.findAll();
    }
    
    public Role findByName(String name) {
        return rr.findByName(name).orElseThrow(() -> new RuntimeException("role not found"));
    }
    
    public Role findById(Long id) {
        return rr.findById(id).orElseThrow(
                () -> new RuntimeException("Role not found exception")
        );
    }

    public void create(Role roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        rr.save(role);
    }

    public void update(Role roleDto) {
        Role role = rr.findById(roleDto.getId()).orElseThrow(
                () -> new RuntimeException("Role not found exception")
        );
        modelMapper.map(roleDto, role);
    }

    public void delete(Long id) {
        rr.deleteById(id);
    }
}
