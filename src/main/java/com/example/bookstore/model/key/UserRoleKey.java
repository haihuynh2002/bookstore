/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author infoh
 */
@Embeddable
@Data
public class UserRoleKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "role_id")
    Long roleId;
}
