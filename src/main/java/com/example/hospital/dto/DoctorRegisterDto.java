package com.example.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRegisterDto {

    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String specialization;
    private Integer experienceYears;

}
