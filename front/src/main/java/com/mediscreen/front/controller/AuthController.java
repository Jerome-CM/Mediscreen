package com.mediscreen.front.controller;

import com.mediscreen.front.beans.ConnexionBean;
import com.mediscreen.front.beans.DoctorBean;
import com.mediscreen.front.beans.RegisterBean;
import com.mediscreen.front.beans.ResponseBean;
import com.mediscreen.front.entity.EnumResponse;
import com.mediscreen.front.proxies.AuthProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    private final AuthProxy authProxy;

    public AuthController(AuthProxy authProxy) {
        this.authProxy = authProxy;
    }

    @GetMapping(value="/")
    public String getHome(Model map){
        map.addAttribute("ConnexionDTO", new DoctorBean());
        map.addAttribute("RegisterDTO", new DoctorBean());
        return "login";
    }

    @GetMapping(value="register")
    public String getRegister(Model map){
        map.addAttribute("ConnexionDTO", new ConnexionBean());
        map.addAttribute("RegisterDTO", new RegisterBean());
        return "login";
    }

    @GetMapping(value="login")
    public String getlogin(Model map){
        map.addAttribute("ConnexionDTO", new ConnexionBean());
        map.addAttribute("RegisterDTO", new RegisterBean());
        return "login";
    }

    @PostMapping(value="login")
    public String login(ConnexionBean co){
        ResponseBean response = authProxy.login(co);
        if (response.getStatus().equals(EnumResponse.OK)){
            return "redirect:/patientList";
        }
        return "login";
    }

    @PostMapping(value="register")
    public String register(RegisterBean register){
        ResponseBean response = authProxy.register(register);
        if (response.getStatus().equals(EnumResponse.OK)){
            return "redirect:/patientList";
        }
        return "login";
    }
}
