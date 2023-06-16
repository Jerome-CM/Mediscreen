package com.mediscreen.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Patient id", example = "123", required = true)
    private Long id;

    @ApiModelProperty(notes = "Patient birthdate", example = "29-06-1990", required = true)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    @ApiModelProperty(notes = "Patient Sex", example = "0 = MAN or 1 = WOMAN", required = true)
    private Sex sex;

    @ApiModelProperty(notes = "Patient address", example = "254 Doctor Street, Elizabeth City, NY 27909", required = false)
    private String address;

    @ApiModelProperty(notes = "Patient phone", example = "0612345678", required = false)
    private String phone;





}
