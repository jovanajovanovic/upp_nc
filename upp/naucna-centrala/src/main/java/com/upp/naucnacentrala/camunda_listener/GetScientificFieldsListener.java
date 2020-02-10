package com.upp.naucnacentrala.camunda_listener;

import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.ScientificRepository;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetScientificFieldsListener implements ExecutionListener {
    @Autowired
    private ScientificRepository scientificRepository;



    @Override
    public void notify(DelegateExecution execution) throws Exception {
        ArrayList<ScientificField> scientifics = (ArrayList<ScientificField>) scientificRepository.findAll();
        System.out.println("Get all scientific fields - LISTENER");
        execution.setVariable("scientifics", scientifics);
    }
}
