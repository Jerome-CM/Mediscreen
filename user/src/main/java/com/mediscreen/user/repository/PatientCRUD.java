package com.mediscreen.user.repository;

import com.mediscreen.user.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientCRUD extends MongoRepository<Patient, Long> {

     public Optional<Patient> findPatientByFirstnameAndLastnameAndBirthdate(String firstname, String lastname, String birthdate);

}
