/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.dto.AdminDto;
import com.example.bookstore.dto.CreateAdminDto;
import com.example.bookstore.model.Admin;
import com.example.bookstore.model.AdminRole;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.key.AdminRoleKey;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.AdminRepository;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author infoh
 */
@Service
@AllArgsConstructor
@Transactional
public class AdminService {

    private static String uploadDirectory = "src/main/resources/static/image/admin/";
    private final AdminRepository ar;
    private final RoleRepository rr;
    private final PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;

    public Admin findById(Long id) {
        return ar.findById(id).orElseThrow(
                () -> new RuntimeException("Admin is not found")
        );
    }

    public AdminDto findAdminDtoById(Long id) {
        return ar.findById(id)
                .map(admin -> modelMapper.map(admin, AdminDto.class))
                .orElseThrow(
                        () -> new RuntimeException("Admin not found")
                );
    }

    public List<AdminDto> findAll() {
        List<AdminDto> admins = ar.findAll().stream()
                .map(admin -> {
                    AdminDto dto = modelMapper.map(admin, AdminDto.class);
                    Set<Role> roles = admin.getAdminRoles().stream().map(adminRole -> adminRole.getRole())
                            .collect(Collectors.toSet());
                    dto.setRoles(roles);
                    return dto;
                })
                .collect(Collectors.toList());
        return admins;
    }

    public String saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
        Files.write(fileNameAndPath, file.getBytes());
        return "/image/admin/" + fileName;
    }

    public void update(AdminDto adminDto) {
        Admin admin = modelMapper.map(adminDto, Admin.class);
        ar.save(admin);
    }

    public void deleteById(Long id) {
        ar.deleteById(id);
    }

    public void create(CreateAdminDto adminDto) {
        Admin admin = modelMapper.map(adminDto, Admin.class);
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        ar.save(admin);

        adminDto.getRoleIds()
                .stream()
                .forEach(id -> {
                    Role role = rr.findById(id).get();
                    
                    AdminRoleKey key = new AdminRoleKey(admin.getId(), id);
                    AdminRole ar = new AdminRole();
                    ar.setId(key);
                    ar.addAdminRole(admin, role);
                });
    }
}
