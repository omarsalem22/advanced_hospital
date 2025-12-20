package com.example.hospital.Services;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.DoctorRepository;
import com.example.hospital.Repositories.PatientRepository;
import com.example.hospital.Repositories.UserRepository;
import com.example.hospital.dto.UserRegistrationDto;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Patient;
import com.example.hospital.entity.User;
import com.example.hospital.enums.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

   public User registerUser(UserRegistrationDto user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        // encode password
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = Role.PATIENT;
        if (user.getRole() != null) {
            try {
                String rolename = user.getRole().toUpperCase();
                role = Role.valueOf(rolename);
            } catch (Exception e) {
                throw new RuntimeException("Invalid Role provided");
            }

        }
        newUser.setRoles(new HashSet<>(Collections.singletonList(role)));
        newUser = userRepository.save(newUser);

       
        if (role == Role.DOCTOR) {
            if (user.getSpecialization() == null || user.getExperienceYears() == null) {
                throw new IllegalArgumentException("Missing required fields for Doctor registration");
            }
            Doctor doctor = Doctor.builder()
                    .user(newUser)
                    .specialization(user.getSpecialization())
                    .experienceYears(user.getExperienceYears())
                    .build();
            doctorRepository.save(doctor);
        } else if (role == Role.PATIENT) {
            if (user.getFullName() == null || user.getAge() == null || user.getGender() == null || user.getAddress() == null || user.getMedicalHistory() == null) {
                throw new IllegalArgumentException("Missing required fields for Patient registration");
            }
            Patient patient = new Patient();
            patient.setUser(newUser);
            patient.setFullName(user.getFullName());
            patient.setAge(user.getAge());
            patient.setGender(user.getGender());
            patient.setAddress(user.getAddress());
            patient.setPhoneNumber(user.getPhoneNumber());
            patient.setMedicalHistory(user.getMedicalHistory());
            patientRepository.save(patient);
        }

        return newUser;

    }


}
