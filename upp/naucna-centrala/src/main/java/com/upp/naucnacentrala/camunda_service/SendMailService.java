package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.InputDataDto;
import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService implements JavaDelegate {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Send mail....");
        String registration = (String) execution.getVariable("username");
        System.out.println(registration);

        //generisemo neki random string
        int length = 5;
        boolean useLetters = true;
        boolean useNumber = true;
        String hashCode = RandomStringUtils.random(length, useLetters, useNumber);

        User u = userRepository.findByUsername(registration);
        u.setCode(hashCode);
        userRepository.save(u);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(u.getEmail());
        mailMessage.setSubject("Registration to scientific center");
        mailMessage.setText(registration +  ",\n Wellcome to scientific center " + ", \n " +
                "Verify your account!\n Your activate code is " + hashCode);

        javaMailSender.send(mailMessage);

    }
}
