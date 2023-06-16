package com.mediscreen.notes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.notes.entity.Sex;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PatientDTO {

    @ApiModelProperty(notes = "Patient id", example = "123", required = false)
    private String id;

    @ApiModelProperty(notes = "Patient firstname", example = "John", required = true)
    private String firstname;

    @ApiModelProperty(notes = "Patient lastname", example = "Doe", required = true)
    private String lastname;

    @ApiModelProperty(notes = "Patient birthdate", example = "29-06-1990", required = true)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    @ApiModelProperty(notes = "Patient Sex", example = "0 = Man or 1 = Woman", required = true)
    private Sex sex;

    @ApiModelProperty(notes = "Patient address", example = "254 Doctor Street, Elizabeth City, NY 27909", required = false)
    private String address;

    @ApiModelProperty(notes = "Patient phone", example = "0612345678", required = false)
    private String phone;

}
