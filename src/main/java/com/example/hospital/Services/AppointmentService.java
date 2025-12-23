package com.example.hospital.Services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.hospital.Repositories.AppointmentRepository;
import com.example.hospital.Repositories.DoctorRepository;
import com.example.hospital.Repositories.PatientRepository;
import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Patient;
import com.example.hospital.enums.AppointmentStatus;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentService {

    private static final int APPOINTMENT_DURATION_MINUTES = 30;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public Appointment bookAppointment(UUID patientId, UUID doctorId, LocalDateTime dateTime) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        LocalDateTime proposedStart = dateTime;

        LocalDateTime proposedEnd = dateTime.plusMinutes(APPOINTMENT_DURATION_MINUTES);

        boolean hasOverlap = appointmentRepository.existsOverlappingAppointment(doctor, proposedStart,
                proposedEnd);

        if (hasOverlap) {
            throw new RuntimeException("The doctor has another appointment during this time.");

        }
        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .dateTime(dateTime)
                .status(AppointmentStatus.BOOKED)
                .build();
        return appointmentRepository.save(appointment);

    }

}
