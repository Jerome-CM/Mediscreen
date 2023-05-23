package com.mediscreen.user.service;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;

public interface PatientService {

    Response savePatient(PatientDTO patientDTO);

    Response updatePatient(PatientDTO patientDTO);

    Response findPatient(String id);

    Response getPatientsList();


}
