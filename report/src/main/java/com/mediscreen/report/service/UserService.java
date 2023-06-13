package com.mediscreen.report.service;

import com.mediscreen.report.entity.Note;
import com.mediscreen.report.entity.Patient;

import java.util.List;

public interface UserService {

    public Patient getPatient(Long id);

    public List<Note> getAllNotesByPatientId(String id);
}
