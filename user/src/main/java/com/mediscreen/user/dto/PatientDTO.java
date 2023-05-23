package com.mediscreen.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.user.entity.Sex;
import lombok.Data;


@Data
public class PatientDTO {

    private String id;

    private String firstname;

    private String lastname;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    private Sex sex;

    private String address;

    private String phone;

}
