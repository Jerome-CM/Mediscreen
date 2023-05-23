package com.mediscreen.notes.repository;

import com.mediscreen.notes.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, Long> {

    List<Note> findAllNoteByPatientId(BigInteger id);

    Optional<Note> findNoteById(BigInteger id);
}
