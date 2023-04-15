package com.mediscreen.user.repository;

import com.mediscreen.user.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCRUD extends MongoRepository<Patient, Long> {
}
