package com.mediscreen.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PatientListDTO {

    private String firstname;

    private String lastname;

    private Date birthdate;

}
