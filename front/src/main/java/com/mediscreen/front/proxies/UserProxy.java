package com.mediscreen.front.proxies;

import com.mediscreen.front.beans.PatientBean;
import com.mediscreen.front.beans.ResponseBean;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${microservice.user}")
public interface UserProxy {
    @PostMapping(value="/addPatient")
    @Headers("Content-Type:application/json")
    ResponseBean addPatient(PatientBean patient);

    @PostMapping(value="/updatePatient")
    @Headers("Content-Type:application/json")
    ResponseBean updatePatient(PatientBean patient);


    @GetMapping(value="/patientList")
    ResponseBean getPatientsList();

    @GetMapping(value="/patient/{id}")
    ResponseBean getPatient(@RequestParam("id") String id);


}
