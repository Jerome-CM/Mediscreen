package com.mediscreen.user.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "patient")
public class Patient {

    @Id
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private Date birthdate;

    @NotNull
    private String sex;

    private String address;

    private String phone;


    public Patient(String firstname, String lastname, Date birthdate, String sex) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sex = sex;
    }
}
