package com.example.practice.hospitalManagement;

import com.example.practice.hospitalManagement.entity.Appointment;
import com.example.practice.hospitalManagement.repository.AppointmentRepository;
import com.example.practice.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    AppointmentService appointmentService;


    @Test
    public void testCreateAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 12, 25, 10, 00))
                .reason("Cold")
                .build();

        var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);

        System.out.println(newAppointment);
    }

    @Test
    public void testReAssignAppointment() {
        var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(3L, 2L);
        System.out.println(updatedAppointment);
    }

}
