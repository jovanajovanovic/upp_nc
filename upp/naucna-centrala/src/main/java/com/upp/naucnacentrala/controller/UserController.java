package com.upp.naucnacentrala.controller;


import com.upp.naucnacentrala.dto.*;
import com.upp.naucnacentrala.security.TokenUtils;
import com.upp.naucnacentrala.services.ScientificService;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.identity.Authentication;
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

    @GetMapping(path = "/runRegistration", produces = "application/json")
    public @ResponseBody Boolean runRegistration(){
        //ulogujemo guest usera i pokrenemo proces registracije

        identityService.setAuthenticatedUserId("guest");
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("registration");
        runtimeService.setVariable(pi.getProcessInstanceId(), "starter", "guest");
        System.out.println("RUN REGISTRATION PROCES");
      //  System.out.println("Na camundu je ulogovan "+ identityService.getCurrentAuthentication().getUserId());

        return true;
    }

    @PostMapping(path = "/submit/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity submit(@RequestBody List<InputDataDto> data, @PathVariable String taskId ){
        System.out.println(">> SUBMIT TASK: ");
        System.out.println(data);
        HashMap<String, Object> map = this.mapListToDto(data);


        //ovde sad treba samo da postavimo ovog usera za procesnu instancu to je to
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }

    private HashMap<String, Object> mapListToDto(List<InputDataDto> data) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (InputDataDto i: data) {
            map.put(i.getName(), i.getValue());
            System.out.println(i.getName() + " "+ i.getValue());
        }

        return map;
    }



    //metoda koja ce da vrati aktivne taskove, za pokrenuti proces
    @GetMapping(path = "/get/tasks", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> get(HttpServletRequest http){
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");

        String username = "guest";
        if (authToken != null){
            username = this.tokenUtils.getUsernameFromToken(authToken);
        }

        System.out.println("Ulogovan korisnik je: " + username);
//        identityService.setAuthenticatedUserId(username);
//        Authentication a = identityService.getCurrentAuthentication();
//        a.getGroupIds();
        List<Task> tasks= taskService.createTaskQuery().taskAssignee(username).list();
//        List<Task> tasks1 = taskService.createTaskQuery().taskCandidateGroup();
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task t : tasks){
            String s = t.getName().split(" ")[0];
            TaskDto taskDto =new TaskDto(t.getId(), t.getName(), t.getAssignee(), s);
            taskDtos.add(taskDto);
        }

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }



}
