package com.upp.naucnacentrala.controller;

import com.upp.naucnacentrala.dto.*;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.Editor;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.security.TokenUtils;
import com.upp.naucnacentrala.services.EditorService;
import com.upp.naucnacentrala.services.MagazineService;
import com.upp.naucnacentrala.services.ReviewerService;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class MagazineController {
    @Autowired
    IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private EditorService editorService;

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private MagazineService magazineService;


    @GetMapping(path="/runAddMagazine", produces = "application/json")
    public @ResponseBody
    boolean runAddMagazine(HttpServletRequest http){
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");

        String username = this.tokenUtils.getUsernameFromToken(authToken);

        identityService.setAuthenticatedUserId(username);

        ProcessInstance pi = runtimeService.startProcessInstanceByKey("registration_magazine");

        runtimeService.setVariable(pi.getProcessInstanceId(), "starter", username);
        System.out.println("RUN ADD MAGAZINE  PROCES");


        return true;
    }
    @GetMapping(value = "/getMagazine/{issn}", produces = "application/json")
    public @ResponseBody ResponseEntity<Magazine> getMagazine(@PathVariable String issn){
        //napravicemo i da vrati sve recenzente i sve editore za taj magazin
        System.out.println("Get magazine " + issn);
        try {
            Magazine returnMagazine  = this.magazineService.getMagazine(issn);
            return new ResponseEntity<>(returnMagazine, HttpStatus.OK);

        } catch (ObjectNotFound objectNotFound) {
            objectNotFound.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/allMagazines", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Magazine>> getMagazines(){
        List<Magazine> magazines = this.magazineService.getAllMagazines();
        return new ResponseEntity<>(magazines, HttpStatus.OK);
    }
}
