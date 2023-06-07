package com.mediscreen.report.repository;

import com.mediscreen.report.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, Long> {

    List<Note> findNoteByPatientId(String id);

}
