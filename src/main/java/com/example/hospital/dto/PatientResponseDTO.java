package com.example.hospital.dto;

import lombok.Data;

@Data
public class PatientResponseDTO {
// private UUID id;
    private String username;
    private String phoneNumber;
    private String fullName;
    private String gender;
    private String address;
    private String email;

    private int age;
    private String medicalHistory;

}
