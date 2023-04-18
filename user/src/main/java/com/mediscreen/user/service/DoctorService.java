package com.mediscreen.user.service;

import com.mediscreen.user.entity.Doctor;
import com.mediscreen.user.repository.DoctorCRUD;
import jakarta.servlet.http.HttpServletRequest;
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

    public Doctor findDoctorByFirstname(HttpServletRequest request){

        Optional<Doctor> doctorOpt = doctorCRUD.findDoctorByFirstname(request.getParameter("firstname"));
        if(doctorOpt.isPresent()){
            return doctorOpt.get();
        } else {
           return doctorCRUD.save(new Doctor(request.getParameter("firstname"), request.getParameter("lastname")));
        }
    }
}
