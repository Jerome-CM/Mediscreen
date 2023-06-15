package com.mediscreen.front.controller;

import com.mediscreen.front.beans.PatientBean;
import com.mediscreen.front.beans.ResponseBean;
import com.mediscreen.front.entity.EnumResponse;
import com.mediscreen.front.proxies.NoteProxy;
import com.mediscreen.front.proxies.ReportProxy;
import com.mediscreen.front.proxies.UserProxy;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.HttpCookie;
import java.util.List;

@Controller
@Slf4j
public class PatientController {

    private final UserProxy userProxy;

    private final NoteProxy noteProxy;

    private final ReportProxy reportProxy;

    private final ModelMapper modelMapper;

    public PatientController(UserProxy userProxy, NoteProxy noteProxy, ReportProxy reportProxy, ModelMapper modelMapper) {
        this.userProxy = userProxy;
        this.noteProxy = noteProxy;
        this.reportProxy = reportProxy;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value="/addPatient")
    public String getAddPatient(Model map){
        map.addAttribute("patient", new PatientBean());
        return "addPatient";
    }

    @PostMapping(value="/addPatient")
    public String addPatient(PatientBean patient){
        ResponseBean response = userProxy.addPatient(patient);
        if(response.getStatus().equals(EnumResponse.OK)){
            return "redirect:/patientList";
        } else {
            return "addPatient";
        }

    }


    @GetMapping(value="/patientUpdate/{id}")
    public String getPatientDetails(Model map, @PathVariable Long id){
        ResponseBean response = userProxy.getPatient(id);
        if(response.getStatus().equals(EnumResponse.OK)) {
            PatientBean patientBean = modelMapper.map(response.getContent(), PatientBean.class);
            map.addAttribute("patient", patientBean);
        } else {
            map.addAttribute("response", response);
        }
        return "updatePatient";
    }

    @PostMapping(value="/updatePatient")
    public String updatePatient(PatientBean patient){
        ResponseBean response = userProxy.updatePatient(patient);
        if(response.getStatus().equals(EnumResponse.OK)){
            return "redirect:/patientList";
        } else {
            return "addPatient";
        }
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

    @GetMapping(value="patient/{id}")
    public String getPatientInfo(Model map, @PathVariable Long id, @RequestParam(value = "report", required = false) boolean report) {
        ResponseBean response = userProxy.getPatient(id);
        if (response.getStatus().equals(EnumResponse.OK)) {
            PatientBean patientBean = modelMapper.map(response.getContent(), PatientBean.class);
            map.addAttribute("patient", patientBean);
        } else {
            map.addAttribute("response", response);
        }

        // Add all notes
        ResponseBean responseNote = noteProxy.getAllNoteByPatientId(String.valueOf(id));
        if (responseNote.getStatus().equals(EnumResponse.OK)) {
            map.addAttribute("noteList", responseNote.getContent());
        }

        // Generate report
        if (report) {
            ResponseBean responseReport = reportProxy.generateReport(String.valueOf(id));
            if (responseNote.getStatus().equals(EnumResponse.OK)) {
                map.addAttribute("reportResult", responseReport.getContent());
            }
        }

        return "patient";
    }
}
