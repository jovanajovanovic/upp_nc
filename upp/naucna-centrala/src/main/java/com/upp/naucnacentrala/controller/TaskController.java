package com.upp.naucnacentrala.controller;


import com.upp.naucnacentrala.dto.FormFieldsDto;
import com.upp.naucnacentrala.dto.RegisterUserDto;
import com.upp.naucnacentrala.dto.TaskDto;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

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

    @GetMapping(path="/task/getForm/{taskId}", produces = "application/json")
    public @ResponseBody FormFieldsDto getFrom(@PathVariable String taskId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        TaskFormData tfd = formService.getTaskFormData(task.getId());

        List<FormField> properties = tfd.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getId() + fp.getType());
        }
        return new FormFieldsDto(task.getId(), task.getProcessInstanceId(), properties);

    }

    @GetMapping(path = "/task/regUser/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<RegisterUserDto> getRegUser(@PathVariable String taskId){
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = t.getProcessInstanceId();
        RegisterUserDto user = (RegisterUserDto) runtimeService.getVariable(processInstanceId, "registration");
        return new ResponseEntity<RegisterUserDto>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/activateTask/{processId}", produces = "application/json")
    public @ResponseBody FormFieldsDto get(@PathVariable String processId){
        Task task= taskService.createTaskQuery().processInstanceId(processId).singleResult();
        TaskFormData tfd = formService.getTaskFormData(task.getId());

        List<FormField> properties = tfd.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getId() + fp.getType());
        }
        return new FormFieldsDto(task.getId(), task.getProcessInstanceId(), properties);
    }

}
