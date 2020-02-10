package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailUnfinishedReview implements JavaDelegate {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //saljemo poruku sci editoru da odredjeni recenzent nije uradio recenziju
        Article a = (Article) delegateExecution.getVariable("article");
        String username_r = (String) delegateExecution.getVariable("izvrsilac");

        User reviewer = userRepository.findByUsername(username_r);
        String sci_editor = (String)delegateExecution.getVariable("sci_editor");
        User editor = userRepository.findByUsername(sci_editor);

        System.out.println("SLANJE MEJLA O NE ZAVRSENOJ RECENZIJI");
        System.out.println("RECENZENT: " + reviewer.getName() + " " + reviewer.getSurname());


        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(editor.getEmail());
        mailMessage.setSubject("Notification");
        mailMessage.setText("Dear Mr./Ms. " + editor.getName() + " " + editor.getSurname() + ", \n" +
                " The reviewer " + reviewer.getName() + "  " + reviewer.getSurname() + " did not complete the review " +
                " within the given time. Please select a new reviewer.");
        javaMailSender.send(mailMessage);




    }
}
