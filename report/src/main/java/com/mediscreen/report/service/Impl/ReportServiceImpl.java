package com.mediscreen.report.service.Impl;

import com.mediscreen.report.dto.Response;
import com.mediscreen.report.entity.EnumResponse;
import com.mediscreen.report.entity.Note;
import com.mediscreen.report.entity.Patient;
import com.mediscreen.report.entity.Sex;
import com.mediscreen.report.repository.NoteRepository;
import com.mediscreen.report.repository.PatientRepository;
import com.mediscreen.report.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Lazy
    private final NoteRepository noteRepository;

    private final PatientRepository patientRepository;

    public ReportServiceImpl(NoteRepository noteRepository, PatientRepository patientRepository) {
        this.noteRepository = noteRepository;
        this.patientRepository = patientRepository;
    }

    public int riskLevelCalculator(String id) {

        List<Note> notes = noteRepository.findNoteByPatientId(id);
        log.info("size note : {}", notes.size());

        // Liste des mots à rechercher
        String[] warningWords = {"Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal",
                "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps"};

        // Set pour suivre les mots déjà trouvés
        Set<String> foundWords = new HashSet<>();

        if (notes.size() > 0) {

            // Parcours des notes
            for (Note note : notes) {
                for (String word : warningWords) {
                    if (note.getNote().toLowerCase().contains(word.toLowerCase()) && !foundWords.contains(word.toLowerCase())) {
                        foundWords.add(word.toLowerCase());
                    }
                }

            }
            log.info("Words found : {}", foundWords.size());
            return foundWords.size();
        }
        return 99;

        }

    public Response diabetesAlertPrediction(int riskLevel, Long patientId) {

        // Récupérer les informations du patient à partir du repository
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
log.info("risk send : {}", riskLevel); // TODO delete
        if(patientOpt.isPresent() && riskLevel != 99){
            Patient patient = patientOpt.get();

            boolean isPatientAbove30 = over30YearsOld(patient.getBirthdate());
log.info("+ de 30 : {}", isPatientAbove30);// TODO delete
            Sex sex = patient.getSex();

            log.info("Sex : {}", sex);

            String levelReturn = null;
            if (riskLevel == 0) {
                levelReturn = "None";
            } else if (riskLevel == 2 && isPatientAbove30) {
                levelReturn = "Borderline";
            } else if ((riskLevel >= 3 && riskLevel <= 4 && sex == Sex.MAN && !isPatientAbove30)
                    || (riskLevel >= 4 && riskLevel <= 6 && sex == Sex.WOMAN && !isPatientAbove30) // TODO >= 4 && <= 6 Femme mineur à 5 mots ?
                    || (isPatientAbove30 && riskLevel >= 6 && riskLevel <= 7)) {
                levelReturn = "In Danger";
            } else if ((riskLevel >= 5 && sex == Sex.MAN && !isPatientAbove30)
                    || (riskLevel >= 7 && sex == Sex.WOMAN && !isPatientAbove30)
                    || (isPatientAbove30 && riskLevel >= 8)) {
                levelReturn = "Early Onset";
            }

            log.info("Result : {}", levelReturn);
            return new Response(EnumResponse.OK, levelReturn, "");
        }
        return new Response(EnumResponse.ERROR, "Patient don't have note", "Error prediction");
    }


    public boolean over30YearsOld(String birthdate) {
        // Logique pour calculer l'âge à partir de la date de naissance
        LocalDate birthdateObj = LocalDate.parse(birthdate);
        LocalDate now = LocalDate.now();

        Period period = Period.between(birthdateObj, now);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        log.info("Age : {}ans, {}mois, {}jours", years, months, days);

        return years > 30 || (years == 30 && (months > 0 || days > 0));
    }


}
