package com.mediscreen.user.controller;

import com.mediscreen.user.dto.DoctorDTO;
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
    Response saveDoctor(@RequestBody DoctorDTO doctorDTO){
        return doctorService.saveDoctor(doctorDTO);
    }

    @PostMapping(value="/connexion")
    Response connexion(@RequestBody DoctorDTO doctorDTO){
        return doctorService.auth(doctorDTO);
    }
}
