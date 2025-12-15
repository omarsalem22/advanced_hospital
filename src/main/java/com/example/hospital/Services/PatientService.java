package com.example.hospital.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.PatientRepository;
import com.example.hospital.Repositories.UserRepository;
import com.example.hospital.dto.PatientRegistrationDTO;
import com.example.hospital.entity.Patient;
import com.example.hospital.entity.User;
import com.example.hospital.enums.Role;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class PatientService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    public Patient registerPatient(PatientRegistrationDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPhoneNumber(dto.phoneNumber());
        user.setPassword(passwordEncoder.encode(dto.password()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.PATIENT);
        user.setRoles(roles);
        user = userRepository.save(user);

        // now create pationt profile
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFullName(dto.fullName());
        patient.setAge(dto.age());
        patient.setAddress(dto.address());
        patient.setMedicalHistory(dto.medicalHistory());

        return patientRepository.save(patient);

    }

}
