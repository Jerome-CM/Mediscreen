package proxies;

import com.mediscreen.user.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "user", url = "localhost:9001")
public interface UserProxy {
    @PostMapping(value="/addPatient")
    Response addPatient(HttpServletRequest request);

    // Create by intelliJ, not mapping ?
    Response updatePatient(HttpServletRequest request);


    @GetMapping(value="/patientList")
    Response getPatientsList();

    @GetMapping(value="/patient/{firstname}{lastname}{birthdate}")
    Response getPatient(@RequestParam("firstname") String firstname,
                        @RequestParam("lastname") String lastname,
                        @RequestParam("birthdate") String birthdate);

}
