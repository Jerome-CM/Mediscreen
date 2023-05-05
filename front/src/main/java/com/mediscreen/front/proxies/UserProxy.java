package com.mediscreen.front.proxies;

import com.mediscreen.front.beans.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;


@FeignClient(name = "user", url = "${microservice.user}")
public interface UserProxy {
    @PostMapping(value="/addPatient")
    ResponseBean addPatient(HttpServletRequest request); // TODO Prendre le bean @ParamBody

    @PostMapping(value="/updatePatient")
    ResponseBean updatePatient(HttpServletRequest request);


    @GetMapping(value="/patientList")
    ResponseBean getPatientsList();

    @GetMapping(value="/patient/{id}")
    ResponseBean getPatient(@RequestParam("id") BigInteger id);

}
