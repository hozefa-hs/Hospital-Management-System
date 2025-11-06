package com.example.practice.hospitalManagement.service;

import com.example.practice.hospitalManagement.entity.Insurance;
import com.example.practice.hospitalManagement.entity.Patient;
import com.example.practice.hospitalManagement.repository.InsuranceRepository;
import com.example.practice.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);

        patient.setInsurance(insurance);

        insurance.setPatient(patient); //Bidirectional consistency Maintained

        return patient;
    }

    @Transactional
    public Patient disAssociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);

        patient.setInsurance(null);
        return patient;
    }

}
