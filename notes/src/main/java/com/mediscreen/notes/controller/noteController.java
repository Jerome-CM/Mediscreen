package com.mediscreen.notes.controller;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.dto.Response;
import com.mediscreen.notes.entity.Note;
import com.mediscreen.notes.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api("API for Note; Add and update endpoint.")
public class noteController {

    private final ModelMapper modelMapper;

   private final NoteService noteService;

    public noteController(ModelMapper modelMapper, NoteService noteService) {
        this.modelMapper = modelMapper;
        this.noteService = noteService;
    }

    @ApiOperation(value = "Send a form for added a new note", notes = "Return a response content a status, one content and a message")
    @PostMapping(value="addNewNote")
    Response addNewNote(@RequestBody NoteDTO noteDTO){
        Note note = new Note();
        note = modelMapper.map(noteDTO, Note.class);
        return noteService.addNote(note);
    }

    @ApiOperation(value = "Get all notes for this patient", notes = "Return a response content a status, the content with notes and a message")
    @GetMapping(value="/getAllNoteByPatientId/{id}")
    Response getAllNoteByPatientId(@RequestParam("id") String id){
        return noteService.getAllNoteByPatient(id);
    }

    @ApiOperation(value = "Get note for this id", notes = "Return a response content a status, one content and a message")
    @GetMapping(value="/updateNote/{id}")
    Response getUpdateNote(@RequestParam("id") String id){
        return noteService.getNoteById(id);
    }

    @ApiOperation(value = "Send a form for updated a note", notes = "Return a response content a status, one content and a message")
    @PostMapping(value="/updateNote")
    Response postUpdateNote(@RequestBody NoteDTO noteDTO){
        log.info("--- in post update note ms ---");
        Note note = new Note();
        note = modelMapper.map(noteDTO, Note.class);
        return noteService.updateNote(note);
    }
}
