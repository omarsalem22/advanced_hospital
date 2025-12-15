package com.example.hospital.dto;

import java.time.LocalDate;

public record PatientRegistrationDTO(
        String username,
        String password,
        String email,
        String phoneNumber,
        String fullName,
        LocalDate dateOfBirth,
        String gender,
        String address,
        int age,
        String medicalHistory) {
}