package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.RegisterUserDto;
import com.upp.naucnacentrala.exceptions.WrongHashCodeException;
import com.upp.naucnacentrala.model.Reviewer;
import com.upp.naucnacentrala.model.Role;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.model.User;
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

        RegisterUserDto registration = (RegisterUserDto) execution.getVariable("registration");
        System.out.println(registration);

        boolean activate = (boolean) execution.getVariable("activate_status");

        //proverimo da li se slazu kodovi
        User u = userRepository.findByUsername(registration.getUsername());
        List<ScientificField> scientifics = getScientificFields(registration.getScientific());
        if(activate == true){
            //menjamo status korisnika
            u.setRole(Role.REVIEWER);
            userRepository.delete(u);
            Reviewer r = new Reviewer(u.getName(), u.getSurname(), u.getUsername(), u.getPassword(), u.getCity(), u.getCountry(), u.getEmail(), u.getTitle(), u.isActivate(),  u.getRole(), u.getCode(),scientifics);
            reviewerRepository.save(r);
        }
    }

    private List<ScientificField> getScientificFields(List<String> scientific) {
        List<ScientificField> scientificFields = new ArrayList<>();
        for (String s : scientific) {
            scientificFields.add(this.scientificRepository.findByName(s));
        }
        return scientificFields;
    }
}
