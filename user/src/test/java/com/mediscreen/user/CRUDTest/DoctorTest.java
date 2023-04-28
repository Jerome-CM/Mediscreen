package com.mediscreen.user.CRUDTest;

import com.mediscreen.user.dto.DoctorDTO;
import com.mediscreen.user.dto.Response;
import com.mediscreen.user.entity.EnumResponse;
import com.mediscreen.user.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.*;


@SpringBootTest
public class DoctorTest {

    @Autowired
    private DoctorService doctorService;


    public void saveDoctorTest(){
        DoctorDTO doctor = new DoctorDTO();
        doctor.setFirstname("Jerome");
        doctor.setLastname("Bouteveille");
        doctor.setLogin("admin");
        doctor.setPassword("TestPass9!");

        Response responseIsPresent = doctorService.findDoctorByLogin("admin");

        assertEquals("", EnumResponse.ERROR, responseIsPresent.getStatus());

        doctorService.saveDoctor(doctor);

        Response response = doctorService.findDoctorByLogin("admin");
        assertEquals("", EnumResponse.OK, response.getStatus());

        doctor = (DoctorDTO) response.getContent();
        String password = doctor.getPassword();

        assertFalse("", password.equals("TestPass9!"));
    }

    @Test
    public void authDoctorTest(){
        saveDoctorTest();
        DoctorDTO doctor = new DoctorDTO();

        doctor.setLogin("admin");
        doctor.setPassword("TestPass9!");

        Response response = doctorService.auth(doctor);

        assertEquals("", EnumResponse.OK, response.getStatus());
    }
}
