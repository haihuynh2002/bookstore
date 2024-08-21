/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model;

import com.example.bookstore.model.key.AdminRoleKey;
import com.example.bookstore.model.key.CartBookKey;
import com.example.bookstore.model.key.OrderBookKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author infoh
 */
@Entity
@Table(name = "admin_role")
@Setter
@Getter
public class AdminRole {

    @EmbeddedId
    @JsonIgnore
    private AdminRoleKey id = new AdminRoleKey();

    @ManyToOne
    @MapsId("adminId")
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    public void addAdminRole(Admin admin, Role role) {
        this.admin = admin;
        this.role = role;
        role.getAdminRoles().add(this);
        admin.getAdminRoles().add(this);
    }
}