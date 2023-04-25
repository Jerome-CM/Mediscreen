package com.mediscreen.user.dto;

import com.mediscreen.user.entity.Profile;
import lombok.Data;

@Data
public class ConnexionDTO {

    private String login;
    private String password;

    private Profile profile;

}
