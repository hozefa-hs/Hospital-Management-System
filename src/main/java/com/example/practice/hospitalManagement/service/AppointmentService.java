package com.example.practice.hospitalManagement.service;

import com.example.practice.hospitalManagement.entity.Appointment;
import com.example.practice.hospitalManagement.entity.Doctor;
import com.example.practice.hospitalManagement.entity.Patient;
import com.example.practice.hospitalManagement.repository.AppointmentRepository;
import com.example.practice.hospitalManagement.repository.DoctorRepository;
import com.example.practice.hospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have ....");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);  //only to maintain bidirectional consistency

        return  appointmentRepository.save(appointment);
    }


    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); //this will automatically call the update

        doctor.getAppointments().add(appointment); // bidirectional consistency

        return appointment;
    }

}





