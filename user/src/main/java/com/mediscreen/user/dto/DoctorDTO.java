package com.mediscreen.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DoctorDTO {

    @ApiModelProperty(notes = "Doctor id", example = "646cc1d1b50c7212b5cbbbc0", required = false)
    private String id;

    @ApiModelProperty(notes = "Doctor firstname", example = "John", required = true)
    private String firstname;

    @ApiModelProperty(notes = "Doctor lastname", example = "Doe", required = true)
    private String lastname;

    @ApiModelProperty(notes = "Doctor login", example = "Unknown", required = false)
    private String login;

    @ApiModelProperty(notes = "Doctor password", example = "MyPassword", required = false)
    private String password;
}
