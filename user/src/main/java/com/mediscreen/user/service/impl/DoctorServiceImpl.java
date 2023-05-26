package com.mediscreen.user.service.impl;

import com.mediscreen.user.dto.ConnexionDTO;
import com.mediscreen.user.dto.DoctorDTO;
import com.mediscreen.user.dto.RegisterDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Doctor;
import com.mediscreen.user.entity.EnumResponse;
import com.mediscreen.user.repository.DoctorCRUD;

import com.mediscreen.user.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorCRUD doctorCRUD;

   private final ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorCRUD doctorCRUD, ModelMapper modelMapper) {
        this.doctorCRUD = doctorCRUD;
        this.modelMapper = modelMapper;
    }

    /**
     *
     * @param login
     * @return Response
     */
     public Response findDoctorByLogin(String login){
        log.info("--- Method findUserByLogin ---");
        Optional<Doctor> userOpt = doctorCRUD.findDoctorByLogin(login);
        if(userOpt.isPresent()){
            DoctorDTO doctor = modelMapper.map(userOpt.get(), DoctorDTO.class);

            return new Response(EnumResponse.OK, doctor, "");
        } else {
            return new Response(EnumResponse.ERROR, null, "User : " + login + " is unknown");
        }
    }

    /**
     *
     * @param register
     * @return
    */
    public Response saveDoctor(RegisterDTO register){
        log.info("--- Method saveUser ---");
        Response ifExist = findDoctorByLogin(register.getLogin());
        if(ifExist.getStatus().equals(EnumResponse.OK)){
            return new Response(EnumResponse.ERROR, null, "User : " + register.getLogin() + " is already taken");
        } else {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Doctor doctor = modelMapper.map(register, Doctor.class);

            doctor.setPassword(passwordEncoder.encode(register.getPassword()));

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


    /**
     *
     * @param id
     * @return Doctor
    */
    public Doctor getDoctorById(Long id){
        Optional<Doctor> doctor = doctorCRUD.findById(id);
        if(doctor.isPresent()){
            return doctor.get();
        } else {
            throw new RuntimeException("The doctor is not found");
        }
    }

    /**
     *
     * @param co
     * @return Response
    */
    public Response auth(ConnexionDTO co){
        log.info("--- Method auth ---");
        Response ifExist = findDoctorByLogin(co.getLogin());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        DoctorDTO doctor = (DoctorDTO) ifExist.getContent();

        boolean isPasswordMatch = passwordEncoder.matches(co.getPassword(), doctor.getPassword());

        if(ifExist.getStatus().equals(EnumResponse.OK) && isPasswordMatch){

            return new Response(EnumResponse.OK, doctor, "Auth ok ");
        } else {
            return new Response(EnumResponse.ERROR, null, "Bad credentials");
        }
        
    }


}
