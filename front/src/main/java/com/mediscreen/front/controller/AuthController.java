package com.mediscreen.front.controller;

import com.mediscreen.front.beans.ConnexionBean;
import com.mediscreen.front.beans.DoctorBean;
import com.mediscreen.front.beans.RegisterBean;
import com.mediscreen.front.beans.ResponseBean;
import com.mediscreen.front.entity.EnumResponse;
import com.mediscreen.front.proxies.AuthProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@Slf4j
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
    public String login(ConnexionBean co, HttpServletResponse resp){
        ResponseBean response = authProxy.login(co);

        log.info("in auth responses : {}", response);
        if (response.getStatus().equals(EnumResponse.OK)){

            LinkedHashMap<String, Object> content = (LinkedHashMap<String, Object>) response.getContent();
            String first = String.valueOf(content.get("firstname"));
            String last = String.valueOf(content.get("lastname"));

            Cookie cookieFirstname = new Cookie("doctorFirstname", first);
            cookieFirstname.setPath("/");
            cookieFirstname.setMaxAge(3600);
            cookieFirstname.setSecure(true);
            resp.addCookie(cookieFirstname);

            Cookie cookieLastname = new Cookie("doctorLastname", last);
            cookieLastname.setPath("/");
            cookieLastname.setMaxAge(3600);
            cookieLastname.setSecure(true);
            resp.addCookie(cookieLastname);

            return "redirect:/patientList";
        }
        return "redirect:/login";
    }

    @PostMapping(value="register")
    public String register(RegisterBean register){
        ResponseBean response = authProxy.register(register);
        if (response.getStatus().equals(EnumResponse.OK)){
            return "redirect:/patientList";
        }
        return "redirect:/login";
    }

    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model map) {
        // Supprimer les cookies existants
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        map.addAttribute("ConnexionDTO", new DoctorBean());
        map.addAttribute("RegisterDTO", new DoctorBean());

        return "login";
    }
}
