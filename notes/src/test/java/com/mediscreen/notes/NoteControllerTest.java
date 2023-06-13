package com.mediscreen.notes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.notes.controller.NoteController;
import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.entity.Note;
import com.mediscreen.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoteController noteController;

    @Autowired
    private NoteService noteService;

    @Test
    public void saveNewNoteTest() throws Exception {
        Note note = new Note();
        note.setPatientId("1");
        note.setNote("Caution Cholesterol");
        note.setDoctorFullname("Jérôme Bouteveille");
        note.setDateCreated("2023-06-23");

        mockMvc.perform(post("/addNewNote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNoteTest() throws Exception {
        NoteDTO note = new NoteDTO();
        note.setId("6471c345984c131f9d3cb355");
        note.setPatientId("1");
        note.setNote("warning weight");
        note.setDoctorFullname("Jérôme Bouteveille");
        note.setDateCreated("2023-06-23");

        mockMvc.perform(post("/updateNote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isOk());
    }

    /*@Test
    public void getAllNoteByPatientIdTest() throws Exception {
                mockMvc.perform(get("/getAllNoteByPatientId/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getNoteTest() throws Exception {
        mockMvc.perform(get("/updateNote/{id}", "6471c345984c131f9d3cb355"))
                .andExpect(status().isOk());
    }*/
}
