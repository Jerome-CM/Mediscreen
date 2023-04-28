package com.mediscreen.user.service;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;

import java.math.BigInteger;

public interface PatientService {

    Response savePatient(PatientDTO patientDTO);

    Response updatePatient(PatientDTO patientDTO);

    Response findPatient(BigInteger id);

    Response getPatientsList();


}
