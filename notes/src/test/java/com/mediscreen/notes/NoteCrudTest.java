package com.mediscreen.notes;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.dto.Response;
import com.mediscreen.notes.entity.Note;
import com.mediscreen.notes.repository.NoteRepository;
import com.mediscreen.notes.service.NoteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Profile("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NoteCrudTest {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

    @BeforeAll
    public void initNote(){
         Note note = new Note();
         note.setDateCreated("13-03-2023");
         note.setNote("Caution Cholesterol");
         note.setDoctorFullname("Jérôme Bouteveille");
         note.setPatientId("1");

         noteRepository.save(note);
    }

    @Test
    public void saveNewNoteTest(){

        List<Note> notesBefore = noteRepository.findAllNoteByPatientId("1");

        Note note = new Note();
        note.setDateCreated("12-05-2023");
        note.setNote("heart attack last month");
        note.setDoctorFullname("Jérôme Bouteveille");
        note.setPatientId("1");

        noteService.addNote(note);

        List<Note> notesAfter = noteRepository.findAllNoteByPatientId("1");

        assertEquals(notesBefore.size()+1, notesAfter.size());

    }

    @Test
    public void updateNoteTest(){

        // Get id note
        List<Note> noteList = noteRepository.findAllNoteByPatientId("1");
        Note noteBefore = noteList.get(0);
        String id = noteBefore.getId();

        // Change note
        noteBefore.setNote("Poids Anormal");

        noteService.updateNote(noteBefore);

        // Get note after changed
        Response response = noteService.getNoteById(id);

        Note noteAfter = (Note) response.getContent();

        assertEquals("Poids Anormal", noteAfter.getNote());

    }

    @Test
    public void findAllNoteTEst(){

        Response responseAllNote = noteService.getAllNoteByPatient("1");

        List<Note> allNoteByPatient = (List<Note>) responseAllNote.getContent();

        assertTrue(!allNoteByPatient.isEmpty());
    }
}
