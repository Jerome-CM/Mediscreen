package com.mediscreen.user.service;

import com.mediscreen.user.dto.ConnexionDTO;
import com.mediscreen.user.dto.RegisterDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Doctor;

public interface DoctorService {

    Response findDoctorByLogin(String login);

    Response saveDoctor(RegisterDTO doctorDTO);

    Doctor getDoctorById(Long id);

    Response auth(ConnexionDTO doctorDTO);




}
