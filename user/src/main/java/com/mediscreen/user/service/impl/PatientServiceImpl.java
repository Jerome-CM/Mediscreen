package com.mediscreen.user.service;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.EnumResponse;
import com.mediscreen.user.entity.Patient;
import com.mediscreen.user.repository.PatientCRUD;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientService {

    private final PatientCRUD patientCRUD;

    private final ModelMapper modelMapper;

    public PatientService(PatientCRUD patientCRUD, ModelMapper modelMapper) {
        this.patientCRUD = patientCRUD;
        this.modelMapper = modelMapper;
    }

    public Response savePatient(PatientDTO patientDTO){
        log.info("--- Method savePatient");

        if(!patientDTO.getFirstname().isEmpty() &&
           !patientDTO.getLastname().isEmpty() &&
           !patientDTO.getBirthdate().isEmpty() &&
           patientDTO.getSex().toString() == "MAN" || patientDTO.getSex().toString() == "WOMAN"
        ){

            Patient patient = modelMapper.map(patientDTO, Patient.class);
            try {
                patient = patientCRUD.save(patient);
                log.info(" Patient saved : {}", patient);
                return new Response(EnumResponse.OK, null, "Patient saved");
            } catch (Exception e) {
                log.error("Impossible to save a new patient : {}", e.getMessage());
                return new Response(EnumResponse.ERROR, null, "Patient can't be saved");
            }
        } else {
            return new Response(EnumResponse.ERROR, null, "Error in Firstname, Lastname, Birthdate or Sex field");
        }
    }

    public Response updatePatient(PatientDTO patientDTO){
        log.info("--- Method updatePatient");

        Optional<Patient> patientOpt = patientCRUD.findPatientById(patientDTO.getId());

        if(patientOpt.isPresent()){
            Patient patient = modelMapper.map(patientDTO, Patient.class);

            try{
                patient = patientCRUD.save(patient);
                log.info("Patient update : {}",patient);
                return new Response(EnumResponse.OK, null, "Patient updated");
            } catch (Exception e){
                log.error("Impossible to update this patient : {}", e.getMessage());
                return new Response(EnumResponse.ERROR, null, "Impossible to update this patient");
            }

        } else {
            return new Response(EnumResponse.ERROR, null, "This patient is unknown");
        }


    }

    public Response findPatient(BigInteger id){
        log.info("--- Method findPatient ---");
        Optional<Patient> userOpt = patientCRUD.findPatientById(id);
        if(userOpt.isPresent()){
            return new Response(EnumResponse.OK, userOpt.get(), "");
        } else {
            return new Response(EnumResponse.ERROR, null, "Patient id : " + id + " is unknown");
        }
    }

    public Response getPatientsList(){
        List<PatientDTO> patientListDTO = new ArrayList<>();
        List<Patient> patients = patientCRUD.findAll();
        patients.forEach(p -> {
            PatientDTO patientDTO = modelMapper.map(p, PatientDTO.class);
            patientListDTO.add(patientDTO);
        });

        return new Response(EnumResponse.OK, patientListDTO, "");
    }


}
