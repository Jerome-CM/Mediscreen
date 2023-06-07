package com.mediscreen.report.controller;

import com.mediscreen.report.dto.Response;
import com.mediscreen.report.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    private final ReportService reportService;

    public controller(ReportService reportService) {
        this.reportService = reportService;
    }

// TODO changer controller et nom de méthode
    @GetMapping(value="/generateReport/{id}")
    public Response getUserTest(@PathVariable("id") String id){
        return reportService.diabetesAlertPrediction(reportService.riskLevelCalculator(id), Long.valueOf(id));
    }

}
