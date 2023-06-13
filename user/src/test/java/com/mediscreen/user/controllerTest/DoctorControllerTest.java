package com.mediscreen.user.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.user.controller.DoctorController;
import com.mediscreen.user.dto.ConnexionDTO;
import com.mediscreen.user.dto.RegisterDTO;
import com.mediscreen.user.service.DoctorService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DoctorController doctorController;

    @Autowired
    private DoctorService doctorService;

    @Test
    public void saveDoctorTest() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setLogin("Jerome-CM");
        registerDTO.setPassword("test");
        registerDTO.setFirstname("Jérôme");
        registerDTO.setLastname("Bouteveille");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void loginDoctorTest() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setLogin("Jerome-CM");
        registerDTO.setPassword("test");
        registerDTO.setFirstname("Jérôme");
        registerDTO.setLastname("Bouteveille");

        doctorService.saveDoctor(registerDTO);


        ConnexionDTO connexion = new ConnexionDTO();
        connexion.setLogin("Jerome-CM");
        connexion.setPassword("test");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(connexion)))
                .andExpect(status().isOk());
    }
}
