package proxies;

import beans.PatientBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//TODO Dependance
@FeignClient(name = "user", url = "localhost:9001")
public interface UserProxy {


    // TODO Faire 2 proxy ou mettre les docteur aussi dans celui-la ?
    @GetMapping(value="/patientList")
    List<PatientBean> getPatientsList();

    @GetMapping(value="/patient/{id}")
    PatientBean getPatient(@PathVariable("id") long id);

}
