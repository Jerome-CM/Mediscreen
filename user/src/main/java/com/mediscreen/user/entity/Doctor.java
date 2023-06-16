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

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;

}
