package com.mediscreen.user.controller;

import com.mediscreen.user.entity.Patient;
import com.mediscreen.user.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value="/patientList")
    String getPatientsList(Model map){
        map.addAttribute("patientsList", patientService.getPatientsList());
        return "patientList";
    }

    @GetMapping(value="/patient/{id}")
    String getPatient(Model map, @PathVariable long id){
        Optional<Patient> patient = patientService.getPatientById(id);
        if(patient.isPresent()){
            map.addAttribute("patient", patient.get());
        } else {
            throw new RuntimeException("The patient is not found");
        }

        return "patient";
    }

}
