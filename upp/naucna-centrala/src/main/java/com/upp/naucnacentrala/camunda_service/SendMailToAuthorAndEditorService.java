package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;

@Service
public class SendMailToAuthorAndEditorService implements JavaDelegate {


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SEND MAIL TO AUTHOR AND EDITOR ");
        Article a = (Article) delegateExecution.getVariable("article");
        System.out.println("SEND MAIL TO AUTHOR " + a.getAuthor());
        System.out.println("SEND MAIL TO CHIEF EDITOR " + a.getEditor());

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(a.getAuthor().getEmail());
        mailMessage.setSubject("Notification");
        mailMessage.setText("Dear Mr./Ms. " + a.getAuthor().getName() + " " + a.getAuthor().getSurname() +", \n" +
                " Your article " + a.getTitle() + " has been forwarded for further processing, you will receive information about the article.");

        javaMailSender.send(mailMessage);

        mailMessage.setTo(a.getEditor().getEmail());
        mailMessage.setSubject("Notification");
        mailMessage.setText("Dear Mr./Ms. " + a.getEditor().getName() + " " + a.getEditor().getSurname() +", \n" +
                " The author " + a.getAuthor().getName() + "  " + a.getAuthor().getSurname() + " has added a new article " + a.getTitle() + " to the magazine " + a.getMagazine().getTitle() + ".");

        javaMailSender.send(mailMessage);
    }
}
