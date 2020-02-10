package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Review;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendChangesReviewerService implements JavaDelegate {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SLANJE ODGOVORA NA RECENZIJU RECENZENTIMA ");
        String comment = (String)delegateExecution.getVariable("message");
        List<Review> reviews = (List<Review>) delegateExecution.getVariable("reviews");
        System.out.println("ODGOVOR NA RECENZIJU JE: " + comment);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        for (Review r: reviews) {
            mailMessage.setTo(r.getReviewer().getEmail());
            mailMessage.setSubject("Comment on review");
            mailMessage.setText("Dear Mr./Ms. " + r.getReviewer().getName() + " " + r.getReviewer().getSurname() + ", \n" +
                    " The author " + r.getArticle().getAuthor().getName() + "  " + r.getArticle().getAuthor().getSurname() + " answer on your review.\n " +
                    "The answer is " + comment);
            javaMailSender.send(mailMessage);
        }



    }
}
