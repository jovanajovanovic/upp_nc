package com.upp.naucnacentrala.controller;

import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.services.ScientificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scientific")
public class ScientificController {

    @Autowired
    private ScientificService service;

    @GetMapping(path = "/get", produces = "application/json")
    public @ResponseBody List<ScientificField> get(){
        return this.service.getScientifics();
    }
}
