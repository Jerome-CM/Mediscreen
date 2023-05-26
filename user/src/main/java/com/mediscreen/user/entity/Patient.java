package com.mediscreen.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
public class Patient extends Model {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdate;

    @NotNull(message = "Sex is mandatory")
    private Sex sex;

    private String address;

    private String phone;

}
