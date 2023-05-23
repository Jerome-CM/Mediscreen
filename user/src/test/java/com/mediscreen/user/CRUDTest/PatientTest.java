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

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientTest {

    @Autowired
    private PatientService patientService;

    private static String idBefore;

    /*
    @BeforeAll
    public void initPatientTest(){

        PatientDTO patient = new PatientDTO();
        patient.setFirstname("UserTest");
        patient.setLastname("LastnameTest");
        patient.setBirthdate("06-29-1991");
        patient.setSex(Sex.MAN);
        patient.setAddress("Vineuil");
        patient.setPhone("0612345678");
        Response responseBefore = patientService.savePatient(patient);
        System.out.println(responseBefore.getStatus());
        PatientDTO returnPatient = (PatientDTO) responseBefore.getContent();
        System.out.println(returnPatient);
        idBefore = returnPatient.getId();
    }

    @Test
    public void savePatientTest(){
        PatientDTO patient = new PatientDTO();
        patient.setFirstname("Jerome");
        patient.setLastname("Bouteveille");
        patient.setBirthdate("06-29-1991");
        patient.setSex(Sex.MAN);
        patient.setAddress("Vineuil");
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

        Response response = patientService.findPatient(idBefore);
        PatientDTO patient = (PatientDTO) response.getContent();
        String firstnameOrigin = patient.getFirstname();

        patient.setFirstname("Jack");

        patientService.savePatient(patient);

        Response responsePatientFound = patientService.findPatient(patient.getId());
        PatientDTO patientFound = (PatientDTO) responsePatientFound.getContent();

        assertEquals("", "Jack", patientFound.getFirstname());
        assertFalse("", firstnameOrigin.equals(patientFound.getFirstname()));
    }


     */
}
