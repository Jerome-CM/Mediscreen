package beans;

import lombok.Data;

@Data
public class DoctorBean {

    private Long id;
    private String firstname;
    private String lastname;

    public DoctorBean(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
