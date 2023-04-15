package com.mediscreen.user.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "doctor")
public class Doctor {

    @Id
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    public Doctor(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
