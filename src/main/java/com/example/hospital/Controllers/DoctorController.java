package com.example.hospital.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.Services.DoctorService;
import com.example.hospital.dto.DoctorRegisterDto;
import com.example.hospital.dto.DoctorResponsedto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity registerDoctor(@RequestBody DoctorRegisterDto doctorDto) {
        return ResponseEntity.ok(doctorService.registerDoctor(doctorDto));
    }

    @GetMapping("")

    public List<DoctorResponsedto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
