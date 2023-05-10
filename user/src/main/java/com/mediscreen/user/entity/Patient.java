package com.mediscreen.user.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "patient")
public class Patient extends Model {

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthdate;

    @NotNull(message = "Sex is mandatory")
    private Sex sex;

    private String address;

    private String phone;

}
