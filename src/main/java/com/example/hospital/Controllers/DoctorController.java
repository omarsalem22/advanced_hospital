package com.example.hospital.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.Services.DoctorService;
import com.example.hospital.dto.DoctorResponsedto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors/")
public class DoctorController {
    private final DoctorService doctorService;

    
    @GetMapping("")

    public List<DoctorResponsedto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{specialization}")

    public List<DoctorResponsedto> getDoctorBySpecialization(@PathVariable String specialization) {
        return doctorService.getDoctorbySpecialization(specialization);
    }
}
