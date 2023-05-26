package com.mediscreen.user.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Model {

    @NotNull(message = "Firstname is mandatory")
    private String firstname;

    @NotNull(message = "Lastname is mandatory")
    private String lastname;

}
