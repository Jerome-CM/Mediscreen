package com.mediscreen.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.user.entity.Sex;
import lombok.Data;

import java.math.BigInteger;


@Data
public class PatientDTO {

    private BigInteger id;

    private String firstname;

    private String lastname;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    private Sex sex;

    private String address;

    private String phone;

}
