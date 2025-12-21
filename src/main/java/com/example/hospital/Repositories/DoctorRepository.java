package com.example.hospital.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    List<Doctor> findBySpecialization(String specialization);

    Optional<Doctor> findByUserUsername(String username);


    List<Doctor> findByExperienceYearsGreaterThan(int minYears);


}
