package com.example.hospital.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.PatientRepository;
import com.example.hospital.Repositories.UserRepository;
import com.example.hospital.dto.PatientRegistrationDTO;
import com.example.hospital.dto.PatientResponseDTO;
import com.example.hospital.dto.PatientUpdateDTO;
import com.example.hospital.entity.Patient;
import com.example.hospital.entity.User;
import com.example.hospital.enums.Role;

import jakarta.persistence.EntityNotFoundException;
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
        patient.setGender(dto.gender());
        patient.setAddress(dto.address());
        patient.setPhoneNumber(dto.phoneNumber());
        patient.setMedicalHistory(dto.medicalHistory());

        return patientRepository.save(patient);

    }

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> {
                    PatientResponseDTO dto = new PatientResponseDTO();
                    // dto.setId(patient.getId());

                    dto.setFullName(patient.getFullName());
                    dto.setGender(patient.getGender());
                    dto.setAddress(patient.getAddress());
                    dto.setAge(patient.getAge());
                    dto.setMedicalHistory(patient.getMedicalHistory());

                    User user = patient.getUser();
                    if (user != null) {
                        dto.setUsername(user.getUsername());
                        dto.setEmail(user.getEmail());
                        dto.setPhoneNumber(user.getPhoneNumber());
                    }

                    return dto;
                })
                .toList();
    }

    public PatientResponseDTO updatePatient(UUID patientId, PatientUpdateDTO updateDTO) {
        // 1 find the patient
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        // 2 update it
        if (updateDTO.getFullName() != null) {
            patient.setFullName(updateDTO.getFullName());
        }
        if (updateDTO.getGender() != null) {
            patient.setGender(updateDTO.getGender());
        }
        if (updateDTO.getAddress() != null) {
            patient.setAddress(updateDTO.getAddress());
        }
        if (updateDTO.getAge() > 0) {
            patient.setAge(updateDTO.getAge());
        }
          if (updateDTO.getPhoneNumber() != null) {
                patient.setPhoneNumber(updateDTO.getPhoneNumber());
            }
        if (updateDTO.getMedicalHistory() != null) {
            patient.setMedicalHistory(updateDTO.getMedicalHistory());
        }

        User user = patient.getUser();
        if (user != null) {
            if (updateDTO.getEmail() != null) {
                user.setEmail(updateDTO.getEmail());
            }
            if (updateDTO.getPhoneNumber() != null) {
                user.setPhoneNumber(updateDTO.getPhoneNumber());
            }

        }

        Patient updatedPatient = patientRepository.save(patient);
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setFullName(updatedPatient.getFullName());
        dto.setGender(updatedPatient.getGender());
        dto.setAddress(updatedPatient.getAddress());
        dto.setAge(updatedPatient.getAge());
        dto.setMedicalHistory(updatedPatient.getMedicalHistory());

        if (updatedPatient.getUser() != null) {
            dto.setUsername(updatedPatient.getUser().getUsername());
            dto.setEmail(updatedPatient.getUser().getEmail());
            dto.setPhoneNumber(updatedPatient.getUser().getPhoneNumber());
        }

        return dto;

    }

    private Patient getCurrentPatient() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return patientRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Patient profile not found"));
    }

public void deletePatient(UUID patientId) {
    Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
     new EntityNotFoundException("can't find upatiend"));
     
     patientRepository.delete(patient);

}


public PatientResponseDTO getPatientById(UUID patientId) {

    Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

    PatientResponseDTO dto = new PatientResponseDTO();
    // dto.setId(patient.getId());
    dto.setFullName(patient.getFullName());
    dto.setGender(patient.getGender());
    dto.setAddress(patient.getAddress());
    dto.setAge(patient.getAge());
    dto.setMedicalHistory(patient.getMedicalHistory());

    User user = patient.getUser();
    if (user != null) {
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
    }

    return dto;
}}