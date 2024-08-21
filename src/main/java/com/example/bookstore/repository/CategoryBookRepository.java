/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.repository;

import com.example.bookstore.model.CategoryBook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author infoh
 */
public interface CategoryBookRepository extends JpaRepository<CategoryBook, Long>{
    List<CategoryBook> findAllByCategoryId(Long id);
}
