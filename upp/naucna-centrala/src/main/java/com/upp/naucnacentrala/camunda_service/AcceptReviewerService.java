package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.InputDataDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.*;
import com.upp.naucnacentrala.repository.AuthorRepository;
import com.upp.naucnacentrala.repository.ReviewerRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcceptReviewerService implements JavaDelegate {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private ScientificRepository scientificRepository;


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println(">> PROMENA STATUSA KORISNIKA - SERVISNI TASK");

        String registration = (String) execution.getVariable("username");
        List<String> scientific = (List<String>) execution.getVariable("select_scientific");
        System.out.println(registration);

        boolean activate = (boolean) execution.getVariable("status");

        //proverimo da li se slazu kodovi
        User u = userRepository.findByUsername(registration);
        System.out.println(u);
        List<ScientificField> scientifics = getScientificFields(scientific);
        if(activate == true){
            //menjamo status korisnika
            u.setRole(Role.REVIEWER);
            userRepository.delete(u);
            Reviewer r = new Reviewer(u.getName(), u.getSurname(), u.getUsername(), u.getPassword(), u.getCity(), u.getCountry(), u.getEmail(), u.getTitle(), u.isActivate(),  u.getRole(), u.getCode(),scientifics);
            reviewerRepository.save(r);
        }
    }

    private List<ScientificField> getScientificFields(List<String> scientific) throws ObjectNotFound {
        List<ScientificField> scientificFields = new ArrayList<>();
        for (String s : scientific) {
            scientificFields.add(this.scientificRepository.findByCode(s).orElseThrow(()-> new ObjectNotFound("Scientific field does not exist!")));
        }
        return scientificFields;
    }
}
