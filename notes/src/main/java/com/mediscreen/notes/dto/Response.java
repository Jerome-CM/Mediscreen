package com.mediscreen.notes.dto;

import com.mediscreen.notes.entity.EnumResponse;
import lombok.Data;

@Data
public class Response {

    private EnumResponse status;

    private Object content;

    private String message;

    public Response(EnumResponse status, Object content, String message) {
        this.status = status;
        this.content = content;
        this.message = message;
    }
}
