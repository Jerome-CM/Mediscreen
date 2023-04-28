package com.mediscreen.user.service;

import com.mediscreen.user.dto.DoctorDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Doctor;

public interface DoctorService {

    Response findDoctorByLogin(String login);

    Response saveDoctor(DoctorDTO doctorDTO);

    Doctor getDoctorById(Long id);

    Response auth(DoctorDTO doctorDTO);




}
