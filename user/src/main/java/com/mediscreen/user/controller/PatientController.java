package com.mediscreen.user.controller;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.service.PatientService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api("API for Patient; Create and update endpoint.")
@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "Send a form for added a new patient", notes = "Return a response content a status, one content and a message")
    @PostMapping(value="/addPatient")
    Response addPatient(@RequestBody PatientDTO patientDTO){ //
        return patientService.savePatient(patientDTO);
    }

    @ApiOperation(value = "Send a form for updated a patient", notes = "Return a response content a status, one content and a message")
    @PostMapping(value="/updatePatient")
    Response updatePatient(@RequestBody PatientDTO patientDTO){
        return patientService.updatePatient(patientDTO);
    }


    @ApiOperation(value = "Return the list of all patients", notes = "Return a response content a status, one content and a message")
    @GetMapping(value="/patientList")
    Response getPatientsList(){
        return patientService.getPatientsList();
    }

    @ApiOperation(value = "Get informations for this patient", notes = "Return a response content a status, one content and a message")
    @GetMapping(value="/patient/{id}")
    Response getPatient(@PathVariable("id") @ApiParam(name = "id", value = "Patient id", example = "646cc1d1b50c7212b5cbbbc0") String id) {
        return patientService.findPatient(id);
    }

    @ApiOperation(value = "Change informations for this patient", notes = "Return a response content a status, one content and a message")
    @GetMapping(value="/updatePatient/{id}")
    Response getUpdatePatient(@PathVariable("id") @ApiParam(name = "id", value = "Patient id", example = "646cc1d1b50c7212b5cbbbc0") String id){
        return patientService.findPatient(id);
    }

}
