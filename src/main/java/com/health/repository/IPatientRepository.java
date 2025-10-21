package com.health.repository;

import com.health.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface IPatientRepository extends JpaRepository<Patient, Integer> {
public interface IPatientRepository extends IGenericRepository<Patient, Integer> {
    //Patient save(Patient patient);
}
