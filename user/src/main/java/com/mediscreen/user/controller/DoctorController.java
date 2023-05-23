package com.mediscreen.user.controller;

import com.mediscreen.user.dto.ConnexionDTO;
import com.mediscreen.user.dto.RegisterDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api("API for Doctor; Register and connexion endpoint.")
@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @ApiOperation(value = "Send a form for register", notes = "Return a response content a status, one content and a message")
    @PostMapping(value="/register")
    Response saveDoctor(@RequestBody RegisterDTO register){
        return doctorService.saveDoctor(register);
    }

    @ApiOperation(value = "Send a form for login", notes = "Return a response content a status, one content and a message")
    @PostMapping(value="/login")
    Response connexion(@RequestBody ConnexionDTO co){
        return doctorService.auth(co);
    }
}
