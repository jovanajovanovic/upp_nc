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
public class SendNotificationToScieEditor implements JavaDelegate {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SEND MAIL TO SCI EDITOR");
        Article a = (Article) delegateExecution.getVariable("article");
        String sci_username = (String) delegateExecution.getVariable("sci_editor") ;

        System.out.println("SEND MAIL TO EDITOR " + sci_username);

        User u  = userRepository.findByUsername(sci_username);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(u.getEmail());
        mailMessage.setSubject("Notification");
        mailMessage.setText("Dear Mr./Ms. " + u.getName() + " " + u.getSurname() + ", \n" +
                " The author " + a.getAuthor().getName() + "  " + a.getAuthor().getSurname() + " has added a new article " + a.getTitle() + " to the magazine " + a.getMagazine().getTitle() + ".");
       // javaMailSender.send(mailMessage);
    }

}
