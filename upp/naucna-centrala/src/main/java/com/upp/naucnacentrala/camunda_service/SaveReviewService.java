package com.upp.naucnacentrala.camunda_service;


import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Review;
import com.upp.naucnacentrala.model.Reviewer;
import com.upp.naucnacentrala.repository.ArticleRepository;
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

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //treba da uzmem komentar; recenzenta i to upisem u listu recenzija
        String username_r = (String)delegateExecution.getVariable("izvrsilac");
        String comment = (String)delegateExecution.getVariable("comment");
        Article a = (Article)delegateExecution.getVariable("article");
        System.out.println("RECENZIRANJE RADA " + a.getTitle());
        System.out.println("RECENZENT " + username_r);
        System.out.println("KOMENTAR " + comment);
        System.out.println("-----------------------------------------");
        Reviewer reviewer = reviewerRepository.findByUsername(username_r);
        Article article = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());

        Review r = new Review(comment, article, reviewer);

        List<Review> reviews = (List<Review>) delegateExecution.getVariable("reviews");
        reviews.add(r);
        delegateExecution.setVariable("reviews", reviews);
    }
}
