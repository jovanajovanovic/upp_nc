package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Review;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendMailToCorrectPdf implements JavaDelegate {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SLANJE MEJLA AUTORU DA PDF NIJE U SKLADU SA PRAVILIMA");
        Article article = (Article)delegateExecution.getVariable("article");

        SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(article.getAuthor().getEmail());
            mailMessage.setSubject("Notification");
            mailMessage.setText("Dear Mr./Ms. " + article.getAuthor().getName() + " " +article.getAuthor().getSurname() + ", \n" +
                    " The pdf " + article.getPdf() + " is not correct. Please change pdf\n ");
            javaMailSender.send(mailMessage);




    }
}
