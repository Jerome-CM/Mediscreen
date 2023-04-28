package com.mediscreen.user.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public abstract class Model {

    @Id
    private BigInteger id;

    @NotNull(message = "Firstname is mandatory")
    private String firstname;

    @NotNull(message = "Lastname is mandatory")
    private String lastname;

}
