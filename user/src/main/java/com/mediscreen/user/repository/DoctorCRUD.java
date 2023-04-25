package com.mediscreen.user.repository;

import com.mediscreen.user.entity.Doctor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorCRUD extends MongoRepository<Doctor, Long> {

    public Optional<Doctor> findDoctorByLogin(String login);

}
