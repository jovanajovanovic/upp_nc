package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationAuthorAboutArticle implements JavaDelegate {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SLANJE MEJLA AUTORU o statusu clanka");
        Article article = (Article) delegateExecution.getVariable("article");
        String status = "approved";
        if (article.isReject()) {
            status = "rejected";
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(article.getAuthor().getEmail());
        mailMessage.setSubject("Notification");
        mailMessage.setText("Dear Mr./Ms. " + article.getAuthor().getName() + " " + article.getAuthor().getSurname() + ", \n" +
                " The article " + article.getTitle() + " is " + status + ". ");
        javaMailSender.send(mailMessage);

        boolean addArticle = article.isAccept();
        delegateExecution.setVariable("addArticle", addArticle);
    }
}
