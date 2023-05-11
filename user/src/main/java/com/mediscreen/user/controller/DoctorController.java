package com.mediscreen.user.controller;

import com.mediscreen.user.dto.ConnexionDTO;
import com.mediscreen.user.dto.RegisterDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.service.DoctorService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api("API for Doctor; Register and connexion endpoint.")
@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value="/register")
    Response saveDoctor(@RequestBody RegisterDTO register){
        return doctorService.saveDoctor(register);
    }

    @PostMapping(value="/login")
    Response connexion(@RequestBody ConnexionDTO co){
        return doctorService.auth(co);
    }
}
