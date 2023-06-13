package com.mediscreen.report.controller;

import com.mediscreen.report.dto.Response;
import com.mediscreen.report.service.ReportService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Lazy
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping(value="/generateReport/{id}")
    public Response generateReport(@PathVariable("id") String id){
        return reportService.diabetesAlertPrediction(reportService.riskLevelCalculator(id), Long.valueOf(id));
    }

}
