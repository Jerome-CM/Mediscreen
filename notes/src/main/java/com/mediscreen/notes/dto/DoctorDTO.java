package com.mediscreen.notes.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class DoctorDTO {
    private BigInteger id;

    private String firstname;

    private String lastname;

    private String login;

    private String password;
}
