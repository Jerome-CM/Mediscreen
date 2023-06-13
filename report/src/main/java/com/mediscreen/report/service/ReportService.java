package com.mediscreen.report.service;

import com.mediscreen.report.dto.Response;

public interface ReportService {

    int riskLevelCalculator(String id);

    Response diabetesAlertPrediction(int riskLevel, Long patientId);

    boolean over30YearsOld(String birthdate);

}
