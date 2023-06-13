package com.mediscreen.report;

import com.mediscreen.report.entity.Note;
import com.mediscreen.report.repository.NoteRepository;
import com.mediscreen.report.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Profile("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(value = "/UsersDataTest.sql",executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
public class GetNoteDataTest {

    @Autowired
    private UserService userService;

    @Autowired
    private NoteRepository noteRepository;

    @BeforeAll
    public void initNote(){

            Note note = new Note();
            note.setDateCreated("13-03-2023");
            note.setNote("The patient have a new symptoms : Cholesterol");
            note.setDoctorFullname("Jérôme Bouteveille");
            note.setPatientId("1");

            noteRepository.save(note);

    }

    @Test
    public void findAllNoteTEst(){

        List<Note> responseAllNote = userService.getAllNotesByPatientId("1");

        assertTrue(!responseAllNote.isEmpty());
    }


    @AfterAll
    public void deleteAllNoteByPatient(){
        noteRepository.deleteAll();
    }

}
