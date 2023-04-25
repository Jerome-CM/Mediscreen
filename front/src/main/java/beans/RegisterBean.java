package com.mediscreen.user.dto;

import lombok.Data;

@Data
public class RegisterDTO extends ConnexionDTO{

    private String firstname;

    private String lastname;
}
