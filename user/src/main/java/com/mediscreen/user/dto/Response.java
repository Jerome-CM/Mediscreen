package com.mediscreen.user.dto;

import lombok.Data;

@Data
public class Response {

    private String status = "Ok";

    private Object content;

    private String message = "";

    public Response(String status, Object content, String message) {
        this.status = status;
        this.content = content;
        this.message = message;
    }
}
