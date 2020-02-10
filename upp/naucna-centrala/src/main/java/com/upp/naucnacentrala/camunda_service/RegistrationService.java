package com.upp.naucnacentrala.camunda_service;


import com.upp.naucnacentrala.dto.InputDataDto;
import com.upp.naucnacentrala.exceptions.UserAlreadyExistsException;
import com.upp.naucnacentrala.model.Role;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        String name = (String) execution.getVariable("name");
        String surname = (String) execution.getVariable("surname");
        String email = (String) execution.getVariable("email");
        String password = (String) execution.getVariable("password");
        String username = (String) execution.getVariable("username");
        List<String> scientific = (List<String>) execution.getVariable("scientific");
        String title = (String) execution.getVariable("title");
        boolean reviewer = (Boolean) execution.getVariable("reviewer");
        String city = (String) execution.getVariable("city");
        String country = (String) execution.getVariable("country");

        User findUser = identityService.createUserQuery().userId(username).singleResult();

        if(findUser == null) {

            User newUser = identityService.newUser("");
            newUser.setId(username);
            newUser.setPassword(password);
            newUser.setLastName(surname);
            newUser.setFirstName(name);
            newUser.setEmail(email);

            identityService.saveUser(newUser);
            //istog usera sacuvamo i kod nas u tabeli
            //ivucemo sve naucne oblasti

            com.upp.naucnacentrala.model.User user = new com.upp.naucnacentrala.model.User(name, surname, username, passwordEncoder.encode(password),
            city, country, email, title, false, Role.AUTHOR, "");
            userRepository.save(user);
            execution.setVariable("verification", true);

        } else {
            execution.setVariable("verification", false);
            throw new UserAlreadyExistsException("User already exists!");
        }
    }




}
