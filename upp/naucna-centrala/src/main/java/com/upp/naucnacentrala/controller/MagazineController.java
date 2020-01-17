package com.upp.naucnacentrala.controller;

import com.upp.naucnacentrala.dto.*;
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

        MagazineRegisterDto magazineDto = (MagazineRegisterDto) runtimeService.getVariable(processInstanceId, "magazine");
        if(magazineDto != null){
            //radimo izmenu magazina, menjamo samo neke delove
            magazineDto.setName(magazine.getName());
            magazineDto.setIssn(magazine.getIssn());
            magazineDto.setPayment(magazine.getPayment());
            magazineDto.setScientific(magazine.getScientific());
        }else {
            magazineDto = magazine;
        }

        runtimeService.setVariable(processInstanceId, "magazine", magazineDto);

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
            //editors
        String editors ="";
        if(magazine.getEditors() != null) {
            for (int i = 0; i < magazine.getEditors().size(); i++) {
                editors += magazine.getEditors().get(i);
                if (i != magazine.getEditors().size() - 1)
                    editors += ",";
            }

        }
        map.put("editors", editors);
        //reviewers

        String reviewers ="";
        if(magazine.getReviewers() != null) {
            for (int i = 0; i < magazine.getReviewers().size(); i++) {
                reviewers += magazine.getReviewers().get(i);
                if (i != magazine.getReviewers().size() - 1)
                    reviewers += ",";
            }
        }
        map.put("reviewers", reviewers);
            return map;

    }

    //metoda koja vraca magazin koji se unosi
    @GetMapping(value = "/getMagazine/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<MagazineDto> getMagazine(@PathVariable String taskId){
        Task t = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = t.getProcessInstanceId();
        MagazineRegisterDto magazine = (MagazineRegisterDto) runtimeService.getVariable(processInstanceId, "magazine");
        //napravicemo i da vrati sve recenzente i sve editore za taj magazin
        MagazineDto returnMagazine = this.magazineService.makeMagazineDto(magazine);
        return new ResponseEntity<MagazineDto>(returnMagazine, HttpStatus.OK);
    }

    @PostMapping(value = "/getEditors", produces = "application/json")
    public @ResponseBody ResponseEntity getEditors(@RequestBody List<String> scientifics){
        System.out.println(">> get editors for scientifics => " + scientifics);
        List<EditorReviewerDto> editors = this.editorService.getEditors(scientifics);
        return new ResponseEntity(editors, HttpStatus.OK);
    }

    @PostMapping(value = "/getReviewers", produces = "application/json")
    public @ResponseBody ResponseEntity getReviewers(@RequestBody List<String> scientifics){
        System.out.println(">> get reviewers for scientifics => " + scientifics);
        List<EditorReviewerDto> reviewers = this.reviewerService.getReviewers(scientifics);
        return new ResponseEntity(reviewers, HttpStatus.OK);
    }


    @PostMapping(path = "/addEditBoard/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity addEditBoard(@RequestBody EditBoardDto boardDto, @PathVariable String taskId, HttpServletRequest http){
        System.out.println(">> ADD BOARD MAGAZINE: ");
        System.out.println(boardDto);
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        System.out.println("Ulogovan korisnik je editor: " + username);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();

        MagazineRegisterDto magazine = (MagazineRegisterDto) runtimeService.getVariable(processInstanceId, "magazine");

        magazine.setEditors(boardDto.getEditors());
        magazine.setReviewers(boardDto.getReviewers());

        HashMap<String, Object> map = this.mapListToDto(magazine);

        runtimeService.setVariable(processInstanceId, "magazine", magazine);

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }


    @PostMapping(path = "/checkData/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity checkData(@RequestBody CheckDataDto checkDto, @PathVariable String taskId, HttpServletRequest http){
        System.out.println(">> CHECK  MAGAZINE DATA: ");
        System.out.println(checkDto);
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        System.out.println("Ulogovan korisnik je editor: " + username);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();


        HashMap<String, Object> map = new HashMap<>();
        map.put("ok", checkDto.isOk());

        runtimeService.setVariable(processInstanceId, "ok", checkDto.isOk());

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }


    @PostMapping(path = "/activateMagazine/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity activate(@RequestBody ActivateMagazineDto activateMagazineDto, @PathVariable String taskId, HttpServletRequest http){
        System.out.println(">> ACTIVATE  MAGAZINE: ");
        System.out.println(activateMagazineDto);
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        System.out.println("Ulogovan korisnik je editor: " + username);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();


        HashMap<String, Object> map = new HashMap<>();
        map.put("activate", activateMagazineDto.isActivate());

        runtimeService.setVariable(processInstanceId, "activate", activateMagazineDto.isActivate());

        try {
            formService.submitTaskForm(taskId, map);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (HttpStatus.OK);
    }

    @GetMapping(value = "/allMagazines", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Magazine>> getMagazines(){
        List<Magazine> magazines = this.magazineService.getAllMagazines();
        return new ResponseEntity<>(magazines, HttpStatus.OK);
    }
}
