package controller;

import beans.PatientBean;
import beans.ResponseBean;
import entity.EnumResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proxies.UserProxy;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
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

    @PostMapping(value="/updatePatient")
    public void updatePatient(HttpServletRequest request){
        userProxy.updatePatient(request);
    }

    @GetMapping(value="/patient/{id}")
    public String getPatientDetails(Model map, @RequestParam("id") BigInteger id){
        ResponseBean response = userProxy.getPatient(id);
        if(response.getStatus().equals(EnumResponse.OK)) {
            PatientBean patientBean = modelMapper.map(response.getContent(), PatientBean.class);
            map.addAttribute("patient", patientBean);
        } else {
            map.addAttribute("response", response);
        }
        return "patient";
    }

    @GetMapping(value="/patientList")
    public String getPatientsList(Model map){
        ResponseBean response = userProxy.getPatientsList();
        if(response.getStatus().equals(EnumResponse.OK)){
            List<PatientBean> patientBeanList = (List<PatientBean>) response.getContent();
            map.addAttribute("patients", patientBeanList);
        } else {
            map.addAttribute("response", response);
        }
        return "patientList";
    }


}
