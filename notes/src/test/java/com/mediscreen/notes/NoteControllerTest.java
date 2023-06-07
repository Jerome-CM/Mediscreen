package com.mediscreen.user.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.user.controller.DoctorController;
import com.mediscreen.user.controller.PatientController;
import com.mediscreen.user.dto.ConnexionDTO;
import com.mediscreen.user.dto.PatientDTO;
import com.mediscreen.user.dto.RegisterDTO;
import com.mediscreen.user.entity.Sex;
import com.mediscreen.user.service.DoctorService;
import com.mediscreen.user.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientController patientController;

    @Autowired
    private PatientService patientService;

    @Test
    public void savePatientTest() throws Exception {
        PatientDTO patient = new PatientDTO();
        patient.setFirstname("John");
        patient.setLastname("Doe");
        patient.setBirthdate("06-29-1990");
        patient.setSex(Sex.MAN);
        patient.setAddress("Blois");
        patient.setPhone("0612345678");

        mockMvc.perform(post("/addPatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientListTest() throws Exception {
        mockMvc.perform(get("/patientList"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientTest() throws Exception {
        PatientDTO patient = new PatientDTO();
        patient.setFirstname("John");
        patient.setLastname("Doe");
        patient.setBirthdate("06-29-1990");
        patient.setSex(Sex.MAN);
        patient.setAddress("Blois");
        patient.setPhone("0612345678");
        patient.setId(1L);

        patientService.savePatient(patient);

        mockMvc.perform(get("/patient/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientUpdatePageTest() throws Exception {
        PatientDTO patient = new PatientDTO();
        patient.setFirstname("John");
        patient.setLastname("Doe");
        patient.setBirthdate("06-29-1990");
        patient.setSex(Sex.MAN);
        patient.setAddress("Blois");
        patient.setPhone("0612345678");
        patient.setId(3L);

        patientService.savePatient(patient);
        mockMvc.perform(get("/updatePatient/{id}", 3))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePatientTest() throws Exception {
        PatientDTO patient = new PatientDTO();
        patient.setFirstname("Johnny");
        patient.setLastname("English");
        patient.setBirthdate("06-29-1960");
        patient.setSex(Sex.MAN);
        patient.setAddress("London");
        patient.setPhone("0612345678");

        mockMvc.perform(post("/updatePatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk());
    }



}
