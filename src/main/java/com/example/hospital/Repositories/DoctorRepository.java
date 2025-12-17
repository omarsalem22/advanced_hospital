package com.example.hospital.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, UUID>{
    

}
