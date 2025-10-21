package com.health.repository;

import com.health.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepository {// implements IPatientRepository {
    //@Override
    public Patient save(Patient patient) {
        System.out.println("Patient saved to database");
        return patient;
    }
}
