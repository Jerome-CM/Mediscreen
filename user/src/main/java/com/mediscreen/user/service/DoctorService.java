package com.mediscreen.user.service;

import com.mediscreen.user.entity.Doctor;
import com.mediscreen.user.repository.DoctorCRUD;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorCRUD doctorCRUD;

    public DoctorService(DoctorCRUD doctorCRUD) {
        this.doctorCRUD = doctorCRUD;
    }

    public Doctor getDoctorById(Long id){
        Optional<Doctor> doctor = doctorCRUD.findById(id);
        if(doctor.isPresent()){
            return doctor.get();
        } else {
            throw new RuntimeException("The doctor is not found");
        }
    }

    public Doctor findDoctorByFirstname(Doctor doctor){

        String firstname = doctor.getFirstname();
        Optional<Doctor> doctorOpt = doctorCRUD.findDoctorByFirstname(firstname);
        if(doctorOpt.isPresent()){
            return doctorOpt.get();
        } else {
           return doctorCRUD.save(doctor);
        }
    }
}
