package com.mediscreen.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


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
