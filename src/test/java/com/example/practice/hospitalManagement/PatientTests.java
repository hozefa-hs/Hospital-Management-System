package com.example.practice.hospitalManagement;

import com.example.practice.hospitalManagement.entity.Patient;
import com.example.practice.hospitalManagement.entity.type.BloodGroupType;
import com.example.practice.hospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientRepository() {
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }

    @Test
    public void testPatientRepositoryMethods() {

//        Patient patient = patientRepository.findByName("John Doe");

//        List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(1985, 7, 15), "sarah.wilson@example.com");

//        List<Patient> patientList = patientRepository.findByNameContaining("J");

//        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1991, 2, 15));

//        List<Patient> patientList = patientRepository.findAllPatients();
//        for (Patient patient : patientList) {
//            System.out.println("Hello" + patient);
//        }

        //Pagination Support
        System.out.println("Pagination");
        Page<Patient> patientPage = patientRepository.findAllPatientsWithPagination(PageRequest.of(0, 2));
        for (Patient patient : patientPage) {
            System.out.println(patient);
        }


        List<Object[]> list = patientRepository.countEachBloodGroupType();
        for (Object[] objects : list) {
//            System.out.println(objects[0] + " " + objects[1]);
        }

        int updatedRows = patientRepository.updateNameWithId("Hozefa", 1L);
        System.out.println(updatedRows);

    }

}
