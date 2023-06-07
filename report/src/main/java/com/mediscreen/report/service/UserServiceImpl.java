package com.mediscreen.report.service;

import com.mediscreen.report.entity.Note;
import com.mediscreen.report.entity.Patient;
import com.mediscreen.report.repository.NoteRepository;
import com.mediscreen.report.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final PatientRepository patientRepository;

    private final NoteRepository noteRepository;

    public UserServiceImpl(PatientRepository patientRepository, NoteRepository noteRepository) {
        this.patientRepository = patientRepository;
        this.noteRepository = noteRepository;
    }

    public Patient getPatient(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElse(null);
    }

    public List<Note> getAllNotesByPatientId(String id) {
        List<Note> notesList = noteRepository.findNoteByPatientId(id);
        return notesList;
    }
}
