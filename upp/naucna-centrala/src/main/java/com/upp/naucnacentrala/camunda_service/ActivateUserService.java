package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.InputDataDto;
import com.upp.naucnacentrala.exceptions.WrongHashCodeException;
import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivateUserService implements JavaDelegate {

    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //promenimo status korisnika na true ako su kodovi ispravni
        System.out.println(">> AKTIVACIJA KORISNIKA SERVICE >>");
        String registration = (String) execution.getVariable("username");
        System.out.println(registration);

        String insertHashCode = (String) execution.getVariable("code");
        System.out.println("Hash code: " + insertHashCode);

        //proverimo da li se slazu kodovi
        User u = userRepository.findByUsername(registration);
        System.out.println(u.getCode());
        if(u.getCode().equals(insertHashCode)){
            //menjamo status korisnika
            u.setActivate(true);
            userRepository.save(u);
        }else {
            throw new WrongHashCodeException("Input hash code is wrong!");
        }

    }
}
