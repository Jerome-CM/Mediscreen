package com.mediscreen.user.repository;

import com.mediscreen.user.entity.Doctor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface DoctorCRUD extends CrudRepository<Doctor, Long> {

    public Optional<Doctor> findDoctorByLogin(String login);

}
