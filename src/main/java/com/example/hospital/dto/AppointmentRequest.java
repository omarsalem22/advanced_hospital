package com.example.hospital.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AppointmentRequest(
        @NotNull(message = "Patient ID is required") @Positive(message = "Patient ID must be positive") UUID patientId,

        @NotNull(message = "Doctor ID is required") @Positive(message = "Doctor ID must be positive") UUID doctorId,

        @NotNull(message = "Appointment date and time is required") @FutureOrPresent(message = "Appointment time must be now or in the future") LocalDateTime dateTime) {

}
