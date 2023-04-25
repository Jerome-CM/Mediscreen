package com.mediscreen.user.service;

import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Doctor;
import com.mediscreen.user.repository.DoctorCRUD;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DoctorService {

    private final DoctorCRUD doctorCRUD;

    public DoctorService(DoctorCRUD doctorCRUD) {
        this.doctorCRUD = doctorCRUD;
    }

    public Response findDoctorByLogin(HttpServletRequest request){
        log.info("--- Method findUserByLogin ---");
        Optional<Doctor> userOpt = doctorCRUD.findDoctorByLogin(request.getParameter("login"));
        if(userOpt.isPresent()){
            return new Response("Ok", userOpt.get(), "");
        } else {
            return new Response("Error", null, "User : " + request.getParameter("login") + " is unknown");
        }
    }

    public Response saveDoctor(HttpServletRequest request){
        log.info("--- Method saveUser ---");
        Response ifExist = findDoctorByLogin(request);
        if(ifExist.getStatus().equals("Ok")){
            return new Response("Error", null, "User : " + request.getParameter("login") + " is already taken");
        } else {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Doctor doctor = new Doctor();

            doctor.setFirstname(request.getParameter("firstname"));
            doctor.setLastname(request.getParameter("lastname"));
            doctor.setLogin(request.getParameter("login"));
            doctor.setPassword(passwordEncoder.encode(request.getParameter("password")));

            try{
                doctorCRUD.save(doctor);
                log.info("Doctor saved");
                return new Response("Ok", null, "Doctor saved");
            } catch (Exception e){
                log.error("Impossible to save a doctor : {}", e.getMessage());
                return new Response("Error", null, "The doctor can't be saved");
            }
        }
    }

    public Doctor getDoctorById(Long id){
        Optional<Doctor> doctor = doctorCRUD.findById(id);
        if(doctor.isPresent()){
            return doctor.get();
        } else {
            throw new RuntimeException("The doctor is not found");
        }
    }


}
