package com.mediscreen.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ConnexionDTO {

    @ApiModelProperty(notes = "Your login", example = "UziTime", required = true)
    private String login;
    @ApiModelProperty(notes = "Your password", example = "mySecretPass", required = true)
    private String password;
}
