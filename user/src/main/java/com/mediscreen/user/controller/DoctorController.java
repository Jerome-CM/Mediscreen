package com.mediscreen.user.controller;

import com.mediscreen.user.dto.Response;
import com.mediscreen.user.service.DoctorService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value="/register")
    Response saveUser(HttpServletRequest request){
        return doctorService.saveDoctor(request);
    }

    @PostMapping(value="/connexion")
    public void connexion(HttpServletRequest request){
        doctorService.findDoctorByLogin(request);
    }
}
