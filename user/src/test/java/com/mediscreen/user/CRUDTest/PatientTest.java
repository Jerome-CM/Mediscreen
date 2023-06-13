package com.mediscreen.user.CRUDTest;

import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.Patient;
import com.mediscreen.user.entity.Sex;
import com.mediscreen.user.service.PatientService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Profile("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(value = "/UsersDataTest.sql",executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
public class PatientTest {

    @Autowired
    private PatientService patientService;


    @Test
    public void savePatientTest(){
        PatientDTO patient = new PatientDTO();
        patient.setFirstname("John");
        patient.setLastname("Doe");
        patient.setBirthdate("06-29-1990");
        patient.setSex(Sex.MAN);
        patient.setAddress("Blois");
        patient.setPhone("0612345678");

        Response response = patientService.getPatientsList();
        List<Patient> patientList = (List<Patient>) response.getContent();

        patientService.savePatient(patient);

        Response responseAfterSave = patientService.getPatientsList();
        List<Patient> patientListAfter = (List<Patient>) responseAfterSave.getContent();

        assertEquals("", patientList.size()+1, patientListAfter.size());

    }

   @Test
    public void getPatientTest(){

        Response response = patientService.getPatientsList();
        List<PatientDTO> patientList = (List<PatientDTO>) response.getContent();
        PatientDTO patient = patientList.get(0);

        Response responseUser = patientService.findPatient(patient.getId());
        PatientDTO patientFound = (PatientDTO) responseUser.getContent();

        assertEquals("", patient, patientFound);
    }

    @Test
    public void updatePatientTest(){

        Response response = patientService.findPatient(1L);
        PatientDTO patient = (PatientDTO) response.getContent();
        String firstnameOrigin = patient.getFirstname();

        patient.setFirstname("Jack");

        patientService.updatePatient(patient);

        Response responsePatientFound = patientService.findPatient(patient.getId());
        PatientDTO patientFound = (PatientDTO) responsePatientFound.getContent();

        assertEquals("", "Jack", patientFound.getFirstname());
        assertFalse("", firstnameOrigin.equals(patientFound.getFirstname()));
    }

}
