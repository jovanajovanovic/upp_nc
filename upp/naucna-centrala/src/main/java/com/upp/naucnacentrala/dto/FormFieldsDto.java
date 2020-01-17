package com.upp.naucnacentrala.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.camunda.bpm.engine.form.FormField;

import java.util.List;

@Data
@AllArgsConstructor
public class FormFieldsDto {
    private String taskId;
    private List<FormField> formFields;
    private String processInstanceId;

    public FormFieldsDto(String task_id, String p_id, List<FormField> properties) {
        this.taskId = task_id;
        this.processInstanceId = p_id;
        this.formFields = properties;
    }
}
