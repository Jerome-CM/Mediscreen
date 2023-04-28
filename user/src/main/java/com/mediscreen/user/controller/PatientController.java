package com.mediscreen.user.controller;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.service.PatientService;
import com.mediscreen.user.service.impl.PatientServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Api("API for Patient; CRUD operations")
@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value="/addPatient")
    Response addPatient(@RequestBody PatientDTO patientDTO){
        return patientService.savePatient(patientDTO);
    }

    @PostMapping(value="/updatePatient/{id}")
    Response updatePatient(@RequestBody PatientDTO patientDTO){
        return patientService.updatePatient(patientDTO);
    }

    @ApiOperation(value = "Return the list of all patients")
    @GetMapping(value="/patientList")
    Response getPatientsList(){
        return patientService.getPatientsList();
    }

    @GetMapping(value="/patient/{id}")
    Response getPatient(@PathVariable("id") BigInteger id) {
        return patientService.findPatient(id);
    }

    @GetMapping(value="/updatePatient/{id}")
    Response getUpdatePatient(@PathVariable("id") BigInteger id){
        return patientService.findPatient(id);
    }

}
