/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.model.Category;
import com.example.bookstore.repository.CategoryRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author infoh
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository cr;

    @Autowired
    ModelMapper modelMapper;

    public List<Category> findAll() {
        return cr.findAll();
    }

    public Category findById(Long id) {
        return cr.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
    }

    public void create(Category category) {
        cr.save(category);
    }

    public void update(Category categoryDto) {
        Category category = cr.findById(categoryDto.getId()).orElseThrow(
                () -> new RuntimeException("Category not found")
        );

        modelMapper.map(categoryDto, category);
    }

    public void delete(Long id) {
        cr.deleteById(id);
    }
}
