package com.mediscreen.user.service;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Patient;
import com.mediscreen.user.entity.Sex;
import com.mediscreen.user.repository.PatientCRUD;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    public Response savePatient(HttpServletRequest request){
        log.info("--- Method savePatient");

        Patient patient = new Patient();
        patient.setFirstname(request.getParameter("firstname"));
        patient.setLastname(request.getParameter("lastname"));
        patient.setSex(Sex.valueOf(request.getParameter("sex")));
        patient.setBirthdate(request.getParameter("birthdate"));
        patient.setAddress(request.getParameter("address"));
        patient.setPhone(request.getParameter("phone"));

        try{
            patient = patientCRUD.save(patient);
            log.info(" Patient saved : {}",patient);
            return new Response("Ok", null, "Patient saved");
        } catch (Exception e){
            log.error("Impossible to save a new patient : {}", e.getMessage());
            return new Response("Error", null, "Patient can't be saved");
        }

    }

    public Response updatePatient(HttpServletRequest request){
        log.info("--- Method updatePatient");

        Optional<Patient> patientOpt = patientCRUD.findPatientByFirstnameAndLastnameAndBirthdate(request.getParameter("firstnameOld"), request.getParameter("lastnameOld"), request.getParameter("birthdateOld"));

        if(patientOpt.isPresent()){
            Patient patient = new Patient();
            patient.setId(patientOpt.get().getId());
            patient.setFirstname(request.getParameter("firstname"));
            patient.setLastname(request.getParameter("lastname"));
            patient.setBirthdate(request.getParameter("birthdate"));
            patient.setSex(Sex.valueOf(request.getParameter("sex")));
            patient.setAddress(request.getParameter("address"));
            patient.setPhone(request.getParameter("phone"));

            try{
                patient = patientCRUD.save(patient);
                log.info("Patient update : {}",patient);
                return new Response("Ok", null, "Patient updated");
            } catch (Exception e){
                log.error("Impossible to update this patient : {}", e.getMessage());
                return new Response("Error", null, "Impossible to update this patient");
            }

        } else {
            return new Response("Error", null, "This patient is unknown");
        }


    }

    public Response findPatient(String firstname, String lastname, String birthdate){
        log.info("--- Method findPatient ---");
        Optional<Patient> userOpt = patientCRUD.findPatientByFirstnameAndLastnameAndBirthdate(firstname, lastname, birthdate);
        if(userOpt.isPresent()){
            return new Response("Ok", userOpt.get(), "");
        } else {
            return new Response("Error", null, "Patient : " + firstname + " " + lastname + " born in " + birthdate + " is unknown");
        }
    }

    public Response getPatientsList(){
        List<PatientDTO> patientListDTO = new ArrayList<>();
        List<Patient> patients = patientCRUD.findAll();
        patients.forEach(p -> {
            PatientDTO patientDTO = modelMapper.map(p, PatientDTO.class);
            patientListDTO.add(patientDTO);
        });

        return new Response("Ok", patientListDTO, "");
    }


}
