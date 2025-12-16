package com.example.hospital.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.Services.PatientService;
import com.example.hospital.dto.PatientRegistrationDTO;
import com.example.hospital.dto.PatientResponseDTO;
import com.example.hospital.dto.PatientUpdateDTO;
import com.example.hospital.entity.Patient;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/registerPatient")
    public ResponseEntity<Patient> register(@Valid @RequestBody PatientRegistrationDTO dto) {
        Patient patient = patientService.registerPatient(dto);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/getAllPatient")
    public List<PatientResponseDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PutMapping("/{id}")
    public PatientResponseDTO updatePatient(
            @PathVariable UUID   id,
            @RequestBody PatientUpdateDTO updateDTO) {

        return patientService.updatePatient(id, updateDTO);
    }
}
