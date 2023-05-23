package com.mediscreen.notes.service;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.dto.Response;
import com.mediscreen.notes.entity.Note;

import java.math.BigInteger;

public interface NoteService {

    Response addNote(Note note);

    public Response getAllNoteByPatient(BigInteger id);

    public Response getNoteById(BigInteger id);

    public Response updateNote(Note note);
}
