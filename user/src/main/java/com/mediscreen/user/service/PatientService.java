package com.mediscreen.user.service;

import com.mediscreen.user.entity.Patient;
import com.mediscreen.user.repository.PatientCRUD;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientCRUD patientCRUD;

    public PatientService(PatientCRUD patientCRUD) {
        this.patientCRUD = patientCRUD;
    }

    public List<Patient> getPatientsList(){
        return patientCRUD.findAll();
    }

    public Optional<Patient> getPatientById(Long id){
        return patientCRUD.findById(id);
    }


}
