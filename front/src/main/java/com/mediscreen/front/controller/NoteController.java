package com.mediscreen.front.controller;

import com.mediscreen.front.beans.NoteBean;
import com.mediscreen.front.beans.ResponseBean;
import com.mediscreen.front.entity.EnumResponse;
import com.mediscreen.front.proxies.NoteProxy;
import com.mediscreen.front.proxies.UserProxy;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class NoteController {

    private final UserProxy userProxy;

    private final NoteProxy noteProxy;

    private final ModelMapper modelMapper;

    public NoteController(UserProxy userProxy, NoteProxy noteProxy, ModelMapper modelMapper) {
        this.userProxy = userProxy;
        this.noteProxy = noteProxy;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value="/addNewNote/{id}")
    String getAddNotePage(@PathVariable Long id, Model map, @CookieValue(value = "doctorFirstname") String first, @CookieValue(value = "doctorLastname") String last){

        NoteBean note = new NoteBean();
        // Add id patient
        ResponseBean responsePatient = userProxy.getPatient(id);
        if(responsePatient.getStatus().equals(EnumResponse.OK)){
            note.setPatientId(String.valueOf(id));
        }

        String doctorFullname = null;
        doctorFullname = first + " " + last;

        note.setDoctorFullname(doctorFullname);
        map.addAttribute("noteBean", note);

        return "newNote";
    }

    @PostMapping(value="/addNewNote")
    public String addPatient(NoteBean note){
        ResponseBean response = noteProxy.addNewNote(note);
        if(response.getStatus().equals(EnumResponse.OK)){
            log.info("--- Method Post addNewNote Ok ---");
            return "redirect:/patient/"+ note.getPatientId();
        } else {
            log.info("--- Method Post addNewNote Error ---");
            return "newNote";
        }
    }

    @GetMapping(value="/noteUpdate/{id}")
    public String getUpdateNote(@PathVariable String id, Model map){
        ResponseBean response = noteProxy.getUpdateNote(id);
        if(response.getStatus().equals(EnumResponse.OK)){
            log.info("--- Method Get updateNote Ok ---");
            NoteBean note = modelMapper.map(response.getContent(), NoteBean.class);
            map.addAttribute("noteBean", note);
            return "/updateNote";
        } else {
            log.info("--- Method Get updateNote Error ---");
            return "redirect:/noteUpdate/" + id;
        }
    }

    @PostMapping(value="/updateNote")
    public String postUpdateNote(Model map, NoteBean note){
        log.info("--- in post update / note : {}---", note);
        ResponseBean response = noteProxy.postUpdateNote(note);
        if(response.getStatus().equals(EnumResponse.OK)){
            log.info("--- Method Post updateNote Ok ---");
            return "redirect:/patient/"+ note.getPatientId();
        } else {
            log.info("--- Method Post addNewNote Error ---");
            return "newNote";
        }
    }

}
