package com.example.hospital.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.Services.AppointmentService;
import com.example.hospital.dto.AppointmentRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointments/")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("book")
    public String createAppointment(@RequestBody AppointmentRequest request) {

       appointmentService.bookAppointment(request.patientId(), request.doctorId(),
                request.dateTime());
        return "ok";
    }

}
