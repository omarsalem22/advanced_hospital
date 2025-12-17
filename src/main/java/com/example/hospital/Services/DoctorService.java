package com.example.hospital.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.DoctorRepository;
import com.example.hospital.Repositories.UserRepository;
import com.example.hospital.dto.DoctorRegisterDto;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.User;
import com.example.hospital.enums.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorRegisterDto registerDoctor(DoctorRegisterDto doctorDto) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.DOCTOR);
        // 1 Create user First
        User user = User.builder()
                .username(doctorDto.getUsername())
                .email(doctorDto.getEmail())
                .phoneNumber(doctorDto.getPhoneNumber())
                .password(passwordEncoder.encode(doctorDto.getPassword()))
                .roles(roles)
                .build();
        User savedUser = userRepository.save(user);

        // Create Doctor Entity
        Doctor doctor = Doctor.builder()
                .user(savedUser)
                .specialization(doctorDto.getSpecialization())
                .experienceYears(doctorDto.getExperienceYears())
                .build();
        Doctor savedDoctor = doctorRepository.save(doctor);

        return DoctorRegisterDto.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                 .specialization(doctorDto.getSpecialization())
                .experienceYears(doctorDto.getExperienceYears())
                .build();
    }

}
