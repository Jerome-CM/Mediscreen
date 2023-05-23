package com.mediscreen.front.proxies;

import com.mediscreen.front.beans.NoteBean;
import com.mediscreen.front.beans.ResponseBean;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient(name = "note", url = "${microservice.note}")
public interface NoteProxy {
    @PostMapping(value="/addNewNote")
    @Headers("Content-Type:application/json")
    ResponseBean addNewNote(NoteBean note);

    @GetMapping(value="/getAllNoteByPatientId/{id}")
    ResponseBean getAllNoteByPatientId(@RequestParam("id") BigInteger id);

    @GetMapping(value="/updateNote/{id}")
    ResponseBean getUpdateNote(@RequestParam("id") BigInteger id);

    @PostMapping(value="/updateNote")
    @Headers("Content-Type:application/json")
    ResponseBean postUpdateNote(NoteBean note);

}
