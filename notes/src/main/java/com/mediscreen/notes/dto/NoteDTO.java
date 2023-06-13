package com.mediscreen.notes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NoteDTO {

    @ApiModelProperty(notes = "Note id", example = "646cc1d1b50c7212b5cbbbc0", required = false)
    private String id;

    @ApiModelProperty(notes = "Date created for this note", example = "2023-11-25", required = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private String dateCreated;

    @ApiModelProperty(notes = "The first and lastname of the doctor", example = "John Doe", required = true)
    private String doctorFullname;

    @ApiModelProperty(notes = "The patient Id", example = "310cc1d1b50c7212b5cbb718", required = true)
    private String patientId;

    @ApiModelProperty(notes = "The text of the note", example = "Patient has Cholesterol", required = true)
    private String note;

}
