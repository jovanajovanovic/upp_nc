package com.upp.naucnacentrala.controller;

import com.upp.naucnacentrala.dto.FormFieldsDto;
import com.upp.naucnacentrala.dto.MagazineRegisterDto;
import com.upp.naucnacentrala.dto.RegisterUserDto;
import com.upp.naucnacentrala.security.TokenUtils;
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

    @GetMapping(path="/getAddMagazineForm", produces = "application/json")
    public @ResponseBody
    FormFieldsDto getAddMagazineForm(){
        //vracamo pocetni task za registraciju novog korisnika
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("registration_magazine");
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0); //uzmemo prvi task

        TaskFormData data = formService.getTaskFormData(task.getId());
        List<FormField> properties = data.getFormFields();
        System.out.println("Ispis svih polja koja imamo na formi: ");
        for(FormField fp: properties)
            System.out.println(fp.getId() + " " + fp.getType());

        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }

    @PostMapping(path = "/saveMagazine/{taskId}", produces = "application/json")
    public @ResponseBody
    ResponseEntity saveMagazine(@RequestBody MagazineRegisterDto magazine, @PathVariable String taskId, HttpServletRequest http){
        System.out.println(">> SAVE MAGAZINE: ");
        System.out.println(magazine);
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        System.out.println("Ulogovan korisnik je editor: " + username);

        magazine.setChiefEditor(username);

        HashMap<String, Object> map = this.mapListToDto(magazine);
        //ovde sad treba samo da postavimo ovog usera za procesnu instancu to je to
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, "magazine", magazine);

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }

    private HashMap<String, Object> mapListToDto(MagazineRegisterDto magazine) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", magazine.getName());
            map.put("issn", magazine.getIssn());
            map.put("payment", magazine.getPayment());
            String scientifics = "";
            for (int i = 0; i < magazine.getScientific().size(); i++) {
                scientifics += magazine.getScientific().get(i);
                if (i != magazine.getScientific().size() - 1)
                    scientifics += ",";
            }
            map.put("scientific", scientifics);

            return map;

    }
}
