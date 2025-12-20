package com.example.hospital.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.Services.PatientService;
import com.example.hospital.dto.PatientResponseDTO;
import com.example.hospital.dto.PatientUpdateDTO;

@RestController
@RequestMapping("/api/patients/")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("")
    public List<PatientResponseDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientResponseDTO getPatientById(@PathVariable UUID id) {
        return patientService.getPatientById(id);

    }

    @PutMapping("{id}")
    public PatientResponseDTO updatePatient(
            @PathVariable UUID id,
            @RequestBody PatientUpdateDTO updateDTO) {

        return patientService.updatePatient(id, updateDTO);
    }

    @DeleteMapping("{id}")
    
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
