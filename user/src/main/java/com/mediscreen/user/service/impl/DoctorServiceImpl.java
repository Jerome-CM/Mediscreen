package com.mediscreen.user.service;

import com.mediscreen.user.dto.DoctorDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Doctor;
import com.mediscreen.user.entity.EnumResponse;
import com.mediscreen.user.repository.DoctorCRUD;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.util.Optional;

@Service
@Slf4j
public class DoctorService {

    private final DoctorCRUD doctorCRUD;

   private final ModelMapper modelMapper;

    public DoctorService(DoctorCRUD doctorCRUD, ModelMapper modelMapper) {
        this.doctorCRUD = doctorCRUD;
        this.modelMapper = modelMapper;
    }

    public Response findDoctorByLogin(String login){
        log.info("--- Method findUserByLogin ---");
        Optional<Doctor> userOpt = doctorCRUD.findDoctorByLogin(login);
        if(userOpt.isPresent()){
            return new Response(EnumResponse.OK, userOpt.get(), "");
        } else {
            return new Response(EnumResponse.ERROR, null, "User : " + login + " is unknown");
        }
    }

    public Response saveDoctor(DoctorDTO doctorDTO){
        log.info("--- Method saveUser ---");
        Response ifExist = findDoctorByLogin(doctorDTO.getLogin());
        if(ifExist.getStatus().equals(EnumResponse.OK)){
            return new Response(EnumResponse.ERROR, null, "User : " + doctorDTO.getLogin() + " is already taken");
        } else {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);

            doctor.setPassword(passwordEncoder.encode(doctorDTO.getPassword()));

            try{
                doctorCRUD.save(doctor);
                log.info("Doctor saved");
                return new Response(EnumResponse.OK, null, "Doctor saved");
            } catch (Exception e){
                log.error("Impossible to save a doctor : {}", e.getMessage());
                return new Response(EnumResponse.ERROR, null, "The doctor can't be saved");
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

    public Response auth(DoctorDTO doctorDTO){
        log.info("--- Method auth ---");
        Response ifExist = findDoctorByLogin(doctorDTO.getLogin());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Doctor doctor = (Doctor) ifExist.getContent();

        boolean isPasswordMath = passwordEncoder.matches(doctorDTO.getPassword(), doctor.getPassword());

        if(ifExist.getStatus().equals(EnumResponse.OK) && isPasswordMath){
            return new Response(EnumResponse.OK, doctor, "Auth ok ");
        } else {
            return new Response(EnumResponse.ERROR, null, "Bad credentials");
        }
        
    }


}
