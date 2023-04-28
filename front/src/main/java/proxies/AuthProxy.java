package proxies;


import beans.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "user", url = "${microservice.user}")
public interface AuthProxy {

    @PostMapping(value="register")
    ResponseBean register(HttpServletRequest request);

    @PostMapping(value="login")
    ResponseBean login(HttpServletRequest request);

}
