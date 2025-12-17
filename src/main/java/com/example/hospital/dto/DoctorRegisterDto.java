package com.example.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private String password;
    private String phoneNumber;
    private String email;
    private String specialization;
    private Integer experienceYears;

}
