package com.mediscreen.front.proxies;


import com.mediscreen.front.beans.ConnexionBean;
import com.mediscreen.front.beans.RegisterBean;
import com.mediscreen.front.beans.ResponseBean;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "user", url = "${microservice.user}")
public interface AuthProxy {

    @PostMapping(value="register")
    @Headers("Content-Type:application/json")
    ResponseBean register(RegisterBean register);

    @PostMapping(value="login")
    @Headers("Content-Type:application/json")
    ResponseBean login(ConnexionBean co);

}
