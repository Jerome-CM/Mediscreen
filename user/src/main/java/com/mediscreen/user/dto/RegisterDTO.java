package com.mediscreen.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterDTO extends ConnexionDTO{

    @ApiModelProperty(notes = "Doctor firstname", example = "John", required = true)
    private String firstname;

    @ApiModelProperty(notes = "Doctor firstname", example = "Doe", required = true)
    private String lastname;

}
