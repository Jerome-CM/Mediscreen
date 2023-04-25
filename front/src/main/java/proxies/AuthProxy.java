package proxies;

import com.mediscreen.user.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "user", url = "localhost:9001")
public interface AuthProxy {

    @PostMapping(value="register")
    Response register(HttpServletRequest request);

    @PostMapping(value="login")
    Response login(HttpServletRequest request);



}
