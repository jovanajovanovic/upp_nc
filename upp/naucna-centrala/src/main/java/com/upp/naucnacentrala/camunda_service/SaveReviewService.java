package com.upp.naucnacentrala.camunda_service;


import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Review;
import com.upp.naucnacentrala.model.Reviewer;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.ReviewRepository;
import com.upp.naucnacentrala.repository.ReviewerRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaveReviewService implements JavaDelegate {

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //treba da uzmem komentar; recenzenta i to upisem u listu recenzija
        System.out.println("------------------------------------------------------");
        String username_r = (String)delegateExecution.getVariable("izvrsilac");
        String comment = (String)delegateExecution.getVariable("comment");
        Article a = (Article)delegateExecution.getVariable("article");
        List<Review> reviews = (List<Review>) delegateExecution.getVariable("reviews");
        Review trenutnaRecenzija = (Review) delegateExecution.getVariable("review");
        System.out.println("RECENZIRANJE RADA " + a.getTitle());
        System.out.println("RECENZENT " + trenutnaRecenzija.getReviewer().getUsername());
        System.out.println("KOMENTAR " + comment);

        Reviewer reviewer = reviewerRepository.findByUsername(username_r);
        Article article = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());

        trenutnaRecenzija.setComment(comment);
        trenutnaRecenzija = reviewRepository.save(trenutnaRecenzija);
        System.out.println("RECENZIJA " + trenutnaRecenzija.getReviewer().getUsername() + " "+ trenutnaRecenzija.getComment());
//        List<Review> reviews = (List<Review>) delegateExecution.getVariable("reviews");
//        reviews.add(r);
        delegateExecution.setVariable("reviews", reviews);
        System.out.println("==============================================================");

    }
}
