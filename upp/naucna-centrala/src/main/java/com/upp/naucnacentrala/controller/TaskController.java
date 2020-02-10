package com.upp.naucnacentrala.controller;


import com.upp.naucnacentrala.dto.FormFieldsDto;
import com.upp.naucnacentrala.dto.InputDataDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.services.EditorService;
import com.upp.naucnacentrala.services.ReviewerService;
import com.upp.naucnacentrala.services.ScientificService;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.dmn.instance.InformationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    ScientificService scientificService;

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private EditorService editorService;

    @GetMapping(path="/task/getForm/{taskId}", produces = "application/json")
    public @ResponseBody FormFieldsDto getFrom(@PathVariable String taskId) throws ObjectNotFound {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        TaskFormData tfd = formService.getTaskFormData(task.getId());

        List<InputDataDto> readonlyFields = new ArrayList<>();

        List<FormField> fields = tfd.getFormFields();

        List<FormField> fieldReadonly = new ArrayList<>();
        for(FormField fp : fields){
            for (FormFieldValidationConstraint fc : fp.getValidationConstraints()){
                if(fc.getName().equals("readonly")){
                    fieldReadonly.add(fp);
                }
            }
        }
        List<FormField> properties = new ArrayList<>();
        //prodjemo kroz sva polja i proverimo da li postoje u readonly, ako ne postoje dodamo ih u properties
        for(FormField fp : fields){
            if (!fieldReadonly.contains(fp)){
                properties.add(fp); //ovo su sad sva polja koja nisu readonly
            }
        }

        for (FormField fp : fieldReadonly) {
            System.out.println("Polje " + fp.getLabel() + " je readonly");
            //   Object value = fp.getValue().getValue();
            if (fp.getTypeName().equals("multiselect")){
                //uzmem i izvucem vrednosti iz baze
                List<String> values = scientificService.getScientificFields(fp.getValue().getValue());
                readonlyFields.add(new InputDataDto(fp.getLabel(), values, true));
            }else if (fp.getTypeName().equals("multiselectE")){
                List<String> values = editorService.getEditorsByCode(fp.getValue().getValue());
                readonlyFields.add(new InputDataDto(fp.getLabel(), values, true));
            }else if (fp.getTypeName().equals("multiselectR")){
                List<String> values = reviewerService.getReviewersByCode(fp.getValue().getValue());
                readonlyFields.add(new InputDataDto(fp.getLabel(), values, true));
            }else if (fp.getId().equals("scientific")){
                String values = scientificService.getScientificField(fp.getValue().getValue());
                readonlyFields.add(new InputDataDto(fp.getLabel(), values, false));
            }

            else {
                readonlyFields.add(new InputDataDto(fp.getLabel(), fp.getValue().getValue(), false));
            }
        }

        return new FormFieldsDto(task.getId(), task.getProcessInstanceId(), properties, readonlyFields);

    }






}
