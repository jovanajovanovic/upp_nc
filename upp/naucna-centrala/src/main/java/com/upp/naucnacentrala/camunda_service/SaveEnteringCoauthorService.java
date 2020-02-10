package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.CoAuthor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveEnteringCoauthorService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SAVE CO-AUTHOR INFORMATION");
        //uzmemo unesene podatke i napravimo coauthora
        String name = (String)delegateExecution.getVariable("name");
        String email = (String)delegateExecution.getVariable("email");
        String city = (String)delegateExecution.getVariable("city");
        String country = (String)delegateExecution.getVariable("country");

        CoAuthor ca =  new CoAuthor(name,  city, country, email);
        List<CoAuthor> coauthors = (List<CoAuthor>) delegateExecution.getVariable("coauthors");
        coauthors.add(ca);
        delegateExecution.setVariable("coauthors", coauthors);

    }
}
