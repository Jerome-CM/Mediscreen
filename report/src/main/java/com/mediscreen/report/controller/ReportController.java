package com.mediscreen.report.controller;

import com.mediscreen.report.dto.Response;
import com.mediscreen.report.service.ReportService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Get a level of danger for the patient", notes = "Return a response content a status, one content (a level of danger) and a message")
    @GetMapping(value="/generateReport/{id}")
    public Response generateReport(@PathVariable("id") String id){
        return reportService.diabetesAlertPrediction(reportService.riskLevelCalculator(id), Long.valueOf(id));
    }

}
