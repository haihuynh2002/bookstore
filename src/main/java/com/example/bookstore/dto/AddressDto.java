/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.dto;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author infoh
 */
@Data
@ToString
public class AddressDto {
    Long id;
    Long userId;
    
    String name;
    String type;
    private String address;
    private String street;
    private String city;
    private String province;
    private String country;
    private String zipcode;
}
