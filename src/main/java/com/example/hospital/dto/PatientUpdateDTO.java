package com.example.hospital.dto;

import lombok.Data;

@Data
public class PatientUpdateDTO {

    private String fullName;
    private String gender;
    private String email;

    private String address;
    private int age;
    private String medicalHistory;
    private String phoneNumber;

}
