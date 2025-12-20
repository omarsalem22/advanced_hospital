package com.example.hospital.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
    private String specialization; // for doctors
    private Integer experienceYears; // for doctors
    private String fullName; // for patients        
    private Integer age; // for patients
    private String gender; // for patients
    private String address; // for patients
    private String medicalHistory; // for patients
        

}
