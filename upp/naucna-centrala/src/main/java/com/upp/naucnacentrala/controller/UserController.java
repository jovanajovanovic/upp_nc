package com.upp.naucnacentrala.controller;


import com.upp.naucnacentrala.dto.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
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

    @GetMapping(path="/getRegistrationForm", produces = "application/json")
    public @ResponseBody FormFieldsDto getRegistartionForm(){
        //vracamo pocetni task za registraciju novog korisnika
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("registration");
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0); //uzmemo prvi task

        TaskFormData data = formService.getTaskFormData(task.getId());
        List<FormField> properties = data.getFormFields();
        System.out.println("Ispis svih polja koja imamo na formi: ");
        for(FormField fp: properties)
            System.out.println(fp.getId() + " " + fp.getType());

        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }

    @PostMapping(path = "/register/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity register(@RequestBody RegisterUserDto user, @PathVariable String taskId ){
        System.out.println(">> REGISTER USER: ");
        System.out.println(user);
        HashMap<String, Object> map = this.mapListToDto(user);
        //ovde sad treba samo da postavimo ovog usera za procesnu instancu to je to
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, "registration", user);
        runtimeService.setVariable(processInstanceId, "reviewer", user.isReviewer());

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }

    private HashMap<String, Object> mapListToDto(RegisterUserDto user) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", user.getName());
        map.put("surname", user.getSurname());
        map.put("city", user.getCity());
        map.put("country", user.getCountry());
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("metier", user.getMetier());
        String scientifics = "";
        for(int i =0; i < user.getScientific().size(); i++){
            scientifics += user.getScientific().get(i);
            if (i != user.getScientific().size() - 1)
                scientifics += ",";
        }
        map.put("scientific", scientifics);
        map.put("reviewer", user.isReviewer());

        return map;
    }

    //metoda koja ce da vrati aktivne taskove, za pokrenuti proces
    @GetMapping(path = "/get/tasks", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> get(HttpServletRequest http){
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        System.out.println("Ulogovan korisnik je: " + username);

        List<Task> tasks= taskService.createTaskQuery().taskAssignee(username).list();
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task t : tasks){
            String s = t.getName().split(" ")[0];
            TaskDto taskDto =new TaskDto(t.getId(), t.getName(), t.getAssignee(), s);
            taskDtos.add(taskDto);
        }

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    //metoda za aktivaciju korisnika
    @PostMapping(path="/activateUser/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity activateUser(@RequestBody ActivateUserDto dto, @PathVariable String taskId){
        System.out.println(">> ACTIVATE USER: ");
        System.out.println(dto.getCode());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", dto.getCode());
        //ovde sad treba samo da postavimo ovog usera za procesnu instancu to je to
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        System.out.println(taskId);
        System.out.println(task);
        String processInstanceId = task.getProcessInstanceId();

        runtimeService.setVariable(processInstanceId, "hash_code", dto.getCode());

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }

    //metoda za promenu uloge korisnika
    @PostMapping(path="/acceptReviewer/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity acceptReviewer(@RequestBody AcceptReviewerDto dto, @PathVariable String taskId){
        System.out.println(">> ACTIVATE USER: ");
        System.out.println(dto.isStatus());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("status", dto.isStatus());
        //ovde sad treba samo da postavimo ovog usera za procesnu instancu to je to
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        System.out.println(taskId);
        System.out.println(task);
        String processInstanceId = task.getProcessInstanceId();

        runtimeService.setVariable(processInstanceId, "activate_status", dto.isStatus());

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }

}
