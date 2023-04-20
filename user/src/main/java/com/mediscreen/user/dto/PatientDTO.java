package com.mediscreen.user.dto;

import com.mediscreen.user.entity.Sex;
import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {

    private String firstname;

    private String lastname;

    private Date birthdate;

    private Sex sex;

    private String address;

    private String phone;

}
