package com.mediscreen.notes.service;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.dto.Response;
import com.mediscreen.notes.entity.Note;

public interface NoteService {

    Response addNote(Note note);

    public Response getAllNoteByPatient(String id);

    public Response getNoteById(String id);

    public Response updateNote(Note note);
}
