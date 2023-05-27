package com.mediscreen.user.CRUDTest;

import com.mediscreen.user.dto.ConnexionDTO;
import com.mediscreen.user.dto.DoctorDTO;
import com.mediscreen.user.dto.RegisterDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.EnumResponse;
import com.mediscreen.user.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.util.AssertionErrors.*;

@Profile("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(value = "/UsersDataTest.sql",executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
public class DoctorTest {

    @Autowired
    private DoctorService doctorService;


    @Test
    public void saveADoctor(){

        RegisterDTO doctor = new RegisterDTO();
        doctor.setFirstname("John");
        doctor.setLastname("Doe");
        doctor.setLogin("admin");
        doctor.setPassword("TestPass9!");

        Response responseIsPresent = doctorService.findDoctorByLogin("admin");

        assertEquals("", EnumResponse.ERROR, responseIsPresent.getStatus());

        doctorService.saveDoctor(doctor);

        Response response = doctorService.findDoctorByLogin("admin");
        assertEquals("", EnumResponse.OK, response.getStatus());

        DoctorDTO doctorConnected = (DoctorDTO) response.getContent();
        String password = doctorConnected.getPassword();

        assertFalse("", password.equals("TestPass9!"));
    }

    @Test
    public void authDoctorTest(){

        ConnexionDTO doctorConnexion = new ConnexionDTO();

        doctorConnexion.setLogin("Jerome-CM");
        doctorConnexion.setPassword("test");

        Response response = doctorService.auth(doctorConnexion);

        assertEquals("", EnumResponse.OK, response.getStatus());
    }


}
