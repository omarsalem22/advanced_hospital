package com.example.hospital.Repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Doctor;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query(value = """
            SELECT COUNT(a) > 0
            FROM appointments a
            WHERE a.doctor_id = :#{#doctor.id}
              AND a.status = 'BOOKED'
              AND a.date_time < :proposedEnd
              AND a.date_time + INTERVAL '30 minutes' > :proposedStart
            """, nativeQuery = true)
    boolean existsOverlappingAppointment(
            @Param("doctor") Doctor doctor,
            @Param("proposedStart") LocalDateTime proposedStart,
            @Param("proposedEnd") LocalDateTime proposedEnd);

}
