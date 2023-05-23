package com.mediscreen.user.repository;

import com.mediscreen.user.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientCRUD extends MongoRepository<Patient, String> {

     public Optional<Patient> findPatientById(String id);

}
