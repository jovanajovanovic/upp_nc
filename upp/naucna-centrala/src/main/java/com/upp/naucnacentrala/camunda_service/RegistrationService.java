package com.upp.naucnacentrala.camunda_service;


import com.upp.naucnacentrala.dto.RegisterUserDto;
import com.upp.naucnacentrala.exceptions.UserAlreadyExistsException;
import com.upp.naucnacentrala.model.Role;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService implements JavaDelegate {

    @Autowired
    IdentityService identityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Obrada podataka: ...");
        RegisterUserDto registration = (RegisterUserDto) execution.getVariable("registration");
        System.out.println(registration);

        User findUser = identityService.createUserQuery().userId(registration.getUsername()).singleResult();

        if(findUser == null) {

            User newUser = identityService.newUser("");
            newUser.setId(registration.getUsername());
            newUser.setPassword(registration.getPassword());
            newUser.setLastName(registration.getSurname());
            newUser.setFirstName(registration.getName());
            newUser.setEmail(registration.getEmail());

            identityService.saveUser(newUser);
            //istog usera sacuvamo i kod nas u tabeli
            //ivucemo sve naucne oblasti

            com.upp.naucnacentrala.model.User user = new com.upp.naucnacentrala.model.User(registration.getName(), registration.getSurname(), registration.getUsername(), passwordEncoder.encode(registration.getPassword()), registration.getCity()
            ,registration.getCountry(), registration.getEmail(), registration.getMetier(), false,Role.READER, "");
            userRepository.save(user);
            execution.setVariable("verification", true);

        } else {
            execution.setVariable("verification", false);
            throw new UserAlreadyExistsException("User already exists!");
        }
    }




}
