package com.mediscreen.user.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Doctor extends Model{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Login is mandatory and unique")
    @Column(unique = true)
    private String login;

    @NotNull(message = "Password is mandatory")
    private String password;

}
