package com.mediscreen.notes.service;

import com.mediscreen.notes.dto.Response;
import com.mediscreen.notes.entity.EnumResponse;
import com.mediscreen.notes.entity.Note;
import com.mediscreen.notes.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public Response addNote(Note note){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        note.setDateCreated(date);
        try{
            Note noteSaved = noteRepository.save(note);
            log.info("Note save : {}", noteSaved);
            return new Response(EnumResponse.OK, null, "Note saved");
        } catch (Exception e){
            log.error("Impossible to save the new note : {}", e.getMessage());
            return new Response(EnumResponse.ERROR, null, "Impossible to save a note");
        }

    }

    public Response getAllNoteByPatient(String id){
        log.info("id patient before BDD: {}", id);
        List<Note> allNoteByPatient = noteRepository.findAllNoteByPatientId(id);
        log.info("Notes finded : {}", allNoteByPatient);
        return new Response(EnumResponse.OK, allNoteByPatient, "");
    }

    public Response getNoteById(String id){
        Optional<Note> note = noteRepository.findNoteById(id);
        if(note.isPresent()){
            log.info("Get note with id : {} , {}", id, note);
            return new Response(EnumResponse.OK, note.get(), "");
        }
        return new Response(EnumResponse.ERROR, null, "");
    }

    public Response updateNote(Note note){
        log.info("--- in update service ---");
        Response response = getNoteById(note.getId());
        if(response.getStatus().equals(EnumResponse.OK)){
            try{
                Note content = (Note) response.getContent();
                log.info("notefind : {}",content);

                note.setDateCreated(content.getDateCreated());
                noteRepository.save(note);
                log.info("Note update : {}", note);
                return new Response(EnumResponse.OK, note, "Note updated");
            } catch (Exception e){
                log.error("Impossible to update this note : {}", e.getMessage());
                return new Response(EnumResponse.ERROR, null, "Error to update this note");
            }
        }

        return new Response(EnumResponse.ERROR, null, "Error to update this note");
    }

}
