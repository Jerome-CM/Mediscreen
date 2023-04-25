package beans;

import com.mediscreen.user.dto.ConnexionDTO;
import lombok.Data;

@Data
public class RegisterBean extends ConnexionBean {

    private String firstname;

    private String lastname;
}
