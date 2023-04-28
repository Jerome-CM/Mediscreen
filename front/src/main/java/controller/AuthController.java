package controller;

import beans.DoctorBean;
import beans.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import proxies.AuthProxy;


import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    private final AuthProxy authProxy;

    public AuthController(AuthProxy authProxy) {
        this.authProxy = authProxy;
    }

    @GetMapping(value="/")
    public String home(){
        return "home";
    }

    @GetMapping(value="register")
    public String getRegister(Model map){
        map.addAttribute("ConnexionDTO", new DoctorBean());
        map.addAttribute("RegisterDTO", new DoctorBean());
        return "login";
    }

    @GetMapping(value="login")
    public String getlogin(Model map){
        map.addAttribute("ConnexionDTO", new DoctorBean());
        map.addAttribute("RegisterDTO", new DoctorBean());
        return "login";
    }

    @PostMapping(value="login")
    public ResponseBean login(HttpServletRequest request){
        return authProxy.login(request);
    }

    @PostMapping(value="register")
    public ResponseBean register(HttpServletRequest request){
        return authProxy.register(request);
    }
}
