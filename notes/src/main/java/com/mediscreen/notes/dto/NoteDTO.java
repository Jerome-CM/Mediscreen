package com.mediscreen.notes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigInteger;
import java.util.Date;

@Data
public class NoteDTO {

    private BigInteger id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String dateCreated;

    private String doctorFullname;

    private BigInteger patientId;

    private String note;

}
