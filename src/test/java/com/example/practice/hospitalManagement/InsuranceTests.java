package com.example.practice.hospitalManagement;

import com.example.practice.hospitalManagement.entity.Insurance;
import com.example.practice.hospitalManagement.entity.Patient;
import com.example.practice.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testAssignInsuranceToPatient() {
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_123")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);
    }

    @Test
    void testDisAssociateInsuranceFromPatient() {
        var patient = insuranceService.disAssociateInsuranceFromPatient(1L);
        System.out.println(patient);
    }


}
