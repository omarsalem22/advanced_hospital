package com.example.hospital.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.DoctorRepository;
import com.example.hospital.dto.DoctorResponsedto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class DoctorService {

        private final DoctorRepository doctorRepository;

        public List<DoctorResponsedto> getAllDoctors() {
                return doctorRepository.findAll().stream()
                                .map(doctor -> DoctorResponsedto.builder()
                                                .username(doctor.getUser().getUsername())
                                                .phoneNumber(doctor.getUser().getPhoneNumber())
                                                .specialization(doctor.getSpecialization())
                                                .experienceYears(doctor.getExperienceYears())
                                                .build())
                                .toList();
        }

        public List<DoctorResponsedto> getDoctorbySpecialization(String specialization) {
                return doctorRepository.findBySpecialization(specialization).stream()
                                .map(doctor -> DoctorResponsedto.builder()
                                                .username(doctor.getUser().getUsername())
                                                .phoneNumber(doctor.getUser().getPhoneNumber())
                                                .specialization(doctor.getSpecialization())
                                                .experienceYears(doctor.getExperienceYears())
                                                .build())
                                .toList();
        }

        public List<DoctorResponsedto> searchByMinExperience(int minYears) {
                return doctorRepository.findByExperienceYearsGreaterThan(minYears).stream()
                                .map(doctor -> DoctorResponsedto.builder()
                                                .username(doctor.getUser().getUsername())
                                                .phoneNumber(doctor.getUser().getPhoneNumber())
                                                .specialization(doctor.getSpecialization())
                                                .experienceYears(doctor.getExperienceYears())
                                                .build())
                                .toList();
        }

        public String deleteDoctor(UUID doctorId) {
                doctorRepository.deleteById(doctorId);
                return "Doctor with ID " + doctorId + " has been deleted.";
        }
}
