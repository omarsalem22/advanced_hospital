package com.example.hospital.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="doctor")
public class Doctor {
    @Id
    @GeneratedValue()
    private UUID id ;

    @OneToOne
    @JoinColumn(name="user_id" ,unique=true ,nullable=false)
    private User user;

    private String specialization;

    private Integer experienceYears;


}
