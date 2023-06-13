package com.mediscreen.report;

import com.mediscreen.report.dto.Response;
import com.mediscreen.report.entity.Note;
import com.mediscreen.report.entity.Patient;
import com.mediscreen.report.entity.Sex;
import com.mediscreen.report.repository.NoteRepository;
import com.mediscreen.report.repository.PatientRepository;
import com.mediscreen.report.service.ReportService;
import com.mediscreen.report.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Profile("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(value = "/UsersDataTest.sql",executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
public class ReportResultTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PatientRepository patientRepository;

    private final int NBR_WARNING_WORDS = 7; // Max 10
    @BeforeAll
    public void initNote(){

        noteRepository.deleteAll();
        patientRepository.deleteAll();

        String[] warningWords = {"Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal",
                "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps"};

        for(int i = 0; i < NBR_WARNING_WORDS; i++){
            Note note = new Note();
            note.setDateCreated("13-03-2023");
            note.setNote("The patient have a new symptom : " + warningWords[i]);
            note.setDoctorFullname("Jérôme Bouteveille");
            note.setPatientId("1");

            noteRepository.save(note);
        }

    }

    @Test
    public void over30YearsOldTEst(){

        boolean overTrue = reportService.over30YearsOld("1991-06-29");
        boolean overFalse = reportService.over30YearsOld("2020-01-01");

        assertTrue(overTrue);
        assertFalse(overFalse);
    }

    @Test
    public void riskLevelCalculatorTest(){

        assertEquals(NBR_WARNING_WORDS, reportService.riskLevelCalculator("1"));
    }

    @Test
    public void diabetesAlertPredictionWithManTest(){

        Patient patient = patientRepository.findById(1L).get();
        if(patient != null){
            patient.setSex(Sex.MAN);
            patientRepository.save(patient);
        }

        Response response = reportService.diabetesAlertPrediction(reportService.riskLevelCalculator("1"), 1L);
        String result = (String) response.getContent();

        boolean over30yearsOld = reportService.over30YearsOld(patient.getBirthdate());


        if (!result.equals("Patient don't have note")) {

            if(NBR_WARNING_WORDS == 0){
                assertEquals("None", result);

            } else if (NBR_WARNING_WORDS == 2 && over30yearsOld) {
                assertEquals("Borderline", result);

            } else if (NBR_WARNING_WORDS == 3 && !over30yearsOld) {
                assertEquals("In Danger", result);

            } else if (NBR_WARNING_WORDS == 6 && over30yearsOld) {
                assertEquals("In Danger", result);

            } else if (NBR_WARNING_WORDS == 5 && !over30yearsOld) {
                assertEquals("Early Onset", result);

            } else if (NBR_WARNING_WORDS >= 8 && over30yearsOld) {
                assertEquals("Early Onset", result);
            }

        } else {
            assertEquals("Patient don't have note", result);
        }

    }

    @Test
    public void diabetesAlertPredictionWithWomanTest(){

        Patient patient = patientRepository.findById(1L).get();
        if(patient != null){
            patient.setSex(Sex.WOMAN);
            patientRepository.save(patient);
        }

        Response response = reportService.diabetesAlertPrediction(reportService.riskLevelCalculator("1"), 1L);
        String result = (String) response.getContent();

        boolean over30yearsOld = reportService.over30YearsOld(patient.getBirthdate());

        if (!result.equals("Patient don't have note")) {

            if(NBR_WARNING_WORDS == 0){
                assertEquals("None", result);

            } else if (NBR_WARNING_WORDS == 2 && over30yearsOld) {
                assertEquals("Borderline", result);

            } else if (NBR_WARNING_WORDS == 4 && !over30yearsOld) {
                assertEquals("In Danger", result);

            } else if (NBR_WARNING_WORDS == 6 && over30yearsOld) {
                assertEquals("In Danger", result);

            } else if (NBR_WARNING_WORDS == 7 && !over30yearsOld) {
                assertEquals("Early Onset", result);

            } else if (NBR_WARNING_WORDS >= 8 && over30yearsOld) {
                assertEquals("Early Onset", result);
            }

        } else {
            assertEquals("Patient don't have note", result);
        }

    }


    @AfterAll
    public void deleteAllNoteByPatient(){
        noteRepository.deleteAll();
    }

}
