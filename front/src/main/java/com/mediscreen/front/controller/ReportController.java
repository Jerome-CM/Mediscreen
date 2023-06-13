package com.mediscreen.front.controller;

import com.mediscreen.front.beans.ResponseBean;
import com.mediscreen.front.entity.EnumResponse;
import com.mediscreen.front.proxies.ReportProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReportController {

    private final ReportProxy reportProxy;

    public ReportController(ReportProxy reportProxy) {
        this.reportProxy = reportProxy;
    }

    @GetMapping(value="/generateReport/{id}")
    public String generateReport(@PathVariable String id, Model model){
        ResponseBean response =  reportProxy.generateReport(id);
        if(response.getStatus().equals(EnumResponse.OK)){
            String styleWarningWord = null;
            switch((String) response.getContent()){
                case "None":
                    styleWarningWord = "green";
                    break;
                case "Borderline":
                    styleWarningWord = "yellow";
                    break;
                case "In Danger":
                    styleWarningWord = "orange";
                    break;
                case "Early Onset":
                    styleWarningWord = "red";
                    break;
                default:
                    log.info("Error class");
                    break;
            }
            model.addAttribute("styleWarningWord", styleWarningWord);
            return "redirect:/patient/"+ id + "?report=true";
        }else {
            return "redirect:/patient/"+ id;
        }
    }
}
