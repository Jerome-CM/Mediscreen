package com.mediscreen.notes.controller;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.dto.Response;
import com.mediscreen.notes.entity.Note;
import com.mediscreen.notes.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@Slf4j
public class noteController {

    private final ModelMapper modelMapper;

   private final NoteService noteService;

    public noteController(ModelMapper modelMapper, NoteService noteService) {
        this.modelMapper = modelMapper;
        this.noteService = noteService;
    }


    @PostMapping(value="addNewNote")
    Response addNewNote(@RequestBody NoteDTO noteDTO){
        Note note = new Note();
        note = modelMapper.map(noteDTO, Note.class);
        return noteService.addNote(note);
    }

    @GetMapping(value="/getAllNoteByPatientId/{id}")
    Response getAllNoteByPatientId(@RequestParam("id") BigInteger id){
        return noteService.getAllNoteByPatient(id);
    }

    @GetMapping(value="/updateNote/{id}")
    Response getUpdateNote(@RequestParam("id") BigInteger id){
        return noteService.getNoteById(id);
    }

    @PostMapping(value="/updateNote")
    Response postUpdateNote(@RequestBody NoteDTO noteDTO){
        log.info("--- in post update note ms ---");
        Note note = new Note();
        note = modelMapper.map(noteDTO, Note.class);
        return noteService.updateNote(note);
    }
}
