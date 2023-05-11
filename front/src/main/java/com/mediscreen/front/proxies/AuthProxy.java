package com.mediscreen.front.proxies;


import com.mediscreen.front.beans.ConnexionBean;
import com.mediscreen.front.beans.RegisterBean;
import com.mediscreen.front.beans.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "user", url = "${microservice.user}")
public interface AuthProxy {

    @PostMapping(value="register")
    ResponseBean register(RegisterBean register);

    @PostMapping(value="login")
    ResponseBean login(ConnexionBean co);

}
