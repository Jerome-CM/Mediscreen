package com.mediscreen.notes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.notes.dto.DoctorDTO;
import com.mediscreen.notes.dto.PatientDTO;
import javax.persistence.Id;
import lombok.Data;

import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

@Data
@Document(collection = "note")
public class Note {

    @Id
    private BigInteger id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String dateCreated;

    private String doctorFullname;

    private BigInteger patientId;

    private String note;

}
