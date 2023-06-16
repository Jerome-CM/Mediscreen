package com.mediscreen.report.service.Impl;

import com.mediscreen.report.entity.Note;
import com.mediscreen.report.entity.Patient;
import com.mediscreen.report.repository.NoteRepository;
import com.mediscreen.report.repository.PatientRepository;
import com.mediscreen.report.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PatientRepository patientRepository;

    private final NoteRepository noteRepository;

    public UserServiceImpl(PatientRepository patientRepository, NoteRepository noteRepository) {
        this.patientRepository = patientRepository;
        this.noteRepository = noteRepository;
    }

    /**
     *
     * @param id
     * @return
     */
    public Patient getPatient(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElse(null);
    }

    /**
     *
     * @param id
     * @return
     */
    public List<Note> getAllNotesByPatientId(String id) {
        List<Note> notesList = noteRepository.findNoteByPatientId(id);
        return notesList;
    }
}
