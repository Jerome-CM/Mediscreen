package com.mediscreen.user.controller;

import com.mediscreen.user.service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(value="/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping(value="/connexion")
    public void connexion(HttpServletRequest request){
        doctorService.findDoctorByFirstname(request);
    }
}
