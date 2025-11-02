package com.example.practice.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne                 //Many Appointment to One Patient
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;


    @ManyToOne                 //Many Appointment to One Doctor
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

}

