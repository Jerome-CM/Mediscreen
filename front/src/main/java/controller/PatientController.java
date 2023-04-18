package controller;

import beans.PatientBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import proxies.UserProxy;

import java.util.List;

@Controller
public class PatientController {

    private final UserProxy userProxy;

    public PatientController(UserProxy userProxy) {
        this.userProxy = userProxy;
    }
    @GetMapping(value="/patientList")
    public String getPatientsList(Model map){
        List<PatientBean> patients = userProxy.getPatientsList();
        map.addAttribute("patients", patients);
        return "patientList";
    }

    @GetMapping(value="/patient")
    public String getPatients(Model map, long id){
        PatientBean patient = userProxy.getPatient(id);
        map.addAttribute("patient", patient);
        return "patient";
    }

    @PostMapping(value="addPatient")
    public void addANewPatient(PatientBean patient){
        // TODO Methode save ou methode de User qui appelle save ?
    }
}
