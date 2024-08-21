/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.repository;

import com.example.bookstore.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author infoh
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role>findByName(String name);
    @Query("INSERT INTO Role(name) VALUES(:name)")
    void createRoleByName(String name);
}
