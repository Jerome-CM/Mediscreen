package com.mediscreen.user.entity;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "doctor")
public class Doctor extends Model{

    @NotNull(message = "Login is mandatory and unique")
    @Indexed(unique = true)
    private String login;

    @NotNull(message = "Password is mandatory")
    private String password;

}
