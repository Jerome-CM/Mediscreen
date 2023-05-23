package com.mediscreen.notes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class NoteDTO {

    private String id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String dateCreated;

    private String doctorFullname;

    private String patientId;

    private String note;

}
