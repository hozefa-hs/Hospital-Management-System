package com.example.practice.hospitalManagement.repository;

import com.example.practice.hospitalManagement.entity.Patient;
import com.example.practice.hospitalManagement.entity.type.BloodGroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);

    List<Patient> findByEmail(String mail);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<Patient> findByNameContaining(String name);


    @Query("select p from Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroupType);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("select p.bloodGroup, Count(p) from Patient p group by p.bloodGroup")
    List<Object[]> countEachBloodGroupType();

    @Query(value = "select * from patient_table", nativeQuery = true)
    List<Patient> findAllPatients();

    //Pagination Support
    @Query(value = "select * from patient_table", nativeQuery = true)
    Page<Patient> findAllPatientsWithPagination(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

}
