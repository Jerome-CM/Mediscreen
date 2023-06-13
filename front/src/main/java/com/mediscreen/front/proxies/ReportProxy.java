package com.mediscreen.front.proxies;

import com.mediscreen.front.beans.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "report", url = "${microservice.report}")
public interface ReportProxy {

    @GetMapping(value="/generateReport/{id}")
    ResponseBean generateReport(@PathVariable String id);
}
