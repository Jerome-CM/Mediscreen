package com.mediscreen.notes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.notes.dto.DoctorDTO;
import com.mediscreen.notes.dto.PatientDTO;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "note")
public class Note {

    @Id
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String dateCreated;

    private String doctorFullname;

    private String patientId;

    private String note;

}
