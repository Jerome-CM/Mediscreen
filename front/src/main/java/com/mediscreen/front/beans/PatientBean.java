package com.mediscreen.front.beans;

import com.mediscreen.front.entity.Sex;
import lombok.Data;

@Data
public class PatientBean {
    private String id;

    private String firstname;

    private String lastname;

    private String birthdate;

    private Sex sex;

    private String address;

    private String phone;

    public PatientBean(){

    }
    public PatientBean(String firstname, String lastname, String birthdate, Sex sex, String address, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }
}
