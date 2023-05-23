package com.mediscreen.notes.dto;

import com.mediscreen.notes.entity.EnumResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Response {

    @ApiModelProperty(notes = "State of the response", example = "EnumResponse.OK", required = true)
    private EnumResponse status;
    @ApiModelProperty(notes = "information returned by a method, to pass between the layers", example = "Any Object", required = false)
    private Object content;
    @ApiModelProperty(notes = "Display a message for the user", example = "Patient updated with success", required = false)
    private String message;

    public Response(EnumResponse status, Object content, String message) {
        this.status = status;
        this.content = content;
        this.message = message;
    }
}
