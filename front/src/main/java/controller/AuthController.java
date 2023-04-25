package controller;

import beans.ConnexionBean;
import beans.RegisterBean;
import com.mediscreen.user.dto.Response;
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
    public Response login(HttpServletRequest request){
        return authProxy.login(request);
    }

    @PostMapping(value="register")
    public Response register(HttpServletRequest request){
        return authProxy.register(request);
    }
}
