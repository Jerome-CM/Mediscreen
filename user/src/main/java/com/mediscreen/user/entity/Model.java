package com.mediscreen.user.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class Model {

    @Id
    private Long id;

    @NotNull(message = "Firstname is mandatory")
    private String firstname;

    @NotNull(message = "Lastname is mandatory")
    private String lastname;

}
