package controller;

import beans.PatientBean;
import com.mediscreen.user.dto.Response;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proxies.UserProxy;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    private final UserProxy userProxy;

    private final ModelMapper modelMapper;

    public PatientController(UserProxy userProxy, ModelMapper modelMapper) {
        this.userProxy = userProxy;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value="/addPatient")
    public String getAddPatient(Model map){
        map.addAttribute("patient", new PatientBean());
        return "addPatient";
    }

    @PostMapping(value="/addPatient")
    public void addPatient(HttpServletRequest request){
        userProxy.addPatient(request);
    }

    @GetMapping(value="/updatePatient/{firstname}{lastname}{birthdate}")
    public String getUpdatePatient(Model map,
                                   @RequestParam("firstname") String firstname,
                                   @RequestParam("lastname") String lastname,
                                   @RequestParam("birthdate") String birthdate){
        Response response = userProxy.getPatient(firstname, lastname, birthdate);
        if(response.getStatus().equals("Ok")) {
            PatientBean patientBean = modelMapper.map(response.getContent(), PatientBean.class);
            map.addAttribute("patient", patientBean);
        } else {
            map.addAttribute("response", response);
        }
        return "updatePatient";
    }

    @PostMapping(value="/updatePatient")
    public void updatePatient(HttpServletRequest request){
        userProxy.updatePatient(request);
    }

    @GetMapping(value="/patient")
    public String getPatientDetails(Model map,
                                    @RequestParam("firstname") String firstname,
                                    @RequestParam("lastname") String lastname,
                                    @RequestParam("birthdate") String birthdate){
        Response response = userProxy.getPatient(firstname, lastname, birthdate);
        if(response.getStatus().equals("Ok")) {
            PatientBean patientBean = modelMapper.map(response.getContent(), PatientBean.class);
            map.addAttribute("patient", patientBean);
        } else {
            map.addAttribute("response", response);
        }
        return "patient";
    }

    @GetMapping(value="/patientList")
    public String getPatientsList(Model map){
        Response response = userProxy.getPatientsList();
        if(response.getStatus().equals("Ok")){
            List<PatientBean> patientBeanList = (List<PatientBean>) response.getContent();
            map.addAttribute("patients", patientBeanList);
        } else {
            map.addAttribute("response", response);
        }
        return "patientList";
    }


}
