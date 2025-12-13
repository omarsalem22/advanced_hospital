package com.example.hospital.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;

}
