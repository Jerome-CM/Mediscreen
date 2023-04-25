package beans;

import lombok.Data;

@Data
public class DoctorBean {

    private Long id;
    private String firstname;
    private String lastname;

    private String login;

    private String password;

    public DoctorBean() {
    }

    public DoctorBean(String firstname, String lastname, String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }
}
