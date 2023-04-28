package com.mediscreen.user.repository;

import com.mediscreen.user.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PatientCRUD extends MongoRepository<Patient, BigInteger> {

     public Optional<Patient> findPatientById(BigInteger id);

}
