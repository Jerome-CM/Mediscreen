package com.mediscreen.user.controller;

import com.mediscreen.user.dto.connexionDTO;
import com.mediscreen.user.service.DoctorService;
import jakarta.websocket.server.PathParam;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value="connexion")
    public void connexion(@Value connexionDTO){
        Doctor doctor = doctorService.findDoctorByFirstname();
    }
}
