package com.mediscreen.user.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class Model {

    @Id
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    private String phone;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private Profil profil;


}
