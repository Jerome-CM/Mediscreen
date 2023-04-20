package com.mediscreen.user.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "patient")
public class Patient extends Model {

    @NotNull
    private Date birthdate;

    @NotNull
    private String sex;

    private String address;



}
