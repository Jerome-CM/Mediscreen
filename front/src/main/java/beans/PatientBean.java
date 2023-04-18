package beans;

import lombok.Data;

import java.util.Date;

@Data
public class PatientBean {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String sex;
    private String address;
    private String phone;

    public PatientBean(String firstname, String lastname, Date birthdate, String sex) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sex = sex;
    }
}
