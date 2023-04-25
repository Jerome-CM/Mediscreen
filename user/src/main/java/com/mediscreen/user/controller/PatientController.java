package com.mediscreen.user.controller;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Patient;
import com.mediscreen.user.service.PatientService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value="/addPatient")
    Response addPatient(HttpServletRequest request){
        return patientService.savePatient(request);
    }

    @PostMapping(value="/updatePatient")
    Response updatePatient(HttpServletRequest request){
        return patientService.updatePatient(request);
    }

    @GetMapping(value="/patientList")
    Response getPatientsList(){
        return patientService.getPatientsList();
    }

    @GetMapping(value="/patient/{firstname}{lastname}{birthdate}")
    Response getPatient(@RequestParam("firstname") String firstname,
                        @RequestParam("lastname") String lastname,
                        @RequestParam("birthdate") String birthdate) {
        return patientService.findPatient(firstname, lastname, birthdate);
    }

    @GetMapping(value="/updatePatient/{firstname}{lastname}{birthdate}")
    Response getUpdatePatient(Model map,
                                   @RequestParam("firstname") String firstname,
                                   @RequestParam("lastname") String lastname,
                                   @RequestParam("birthdate") String birthdate){
        return patientService.findPatient(firstname, lastname, birthdate);
    }

}
