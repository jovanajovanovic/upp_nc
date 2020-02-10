package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Review;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.ReviewRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendMailNotificationAuthor implements JavaDelegate {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SLANJE MEJLA AUTORU O POTREBNIM IZMENAMA ");

        Article a = (Article)delegateExecution.getVariable("article");
        System.out.println(a.getTitle());

        Article article = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());

        List<Review> reviews = reviewRepository.findByArticle(article);

        String comments = "";
        for(Review r : reviews){
            comments += r.getReviewer().getName() + " " + r.getReviewer().getSurname() + " - " + r.getComment() + "\n";
        }

        System.out.println(comments);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(a.getAuthor().getEmail());
        mailMessage.setSubject("Notification about change article");
        mailMessage.setText("Dear Mr./Ms. " + a.getAuthor().getName() + " " + a.getAuthor().getSurname() + ", \n" +
                "You need to correct your article in order to be accepted. Bellow the e-mail we send yout" +
                "the opinion of the reviewers about the article.\nComments:\n " + comments);
        javaMailSender.send(mailMessage);

    }
}
