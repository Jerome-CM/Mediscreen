package com.mediscreen.user.repository;

import com.mediscreen.user.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientCRUD extends CrudRepository<Patient, Long> {

     public Optional<Patient> findPatientById(Long id);

}
