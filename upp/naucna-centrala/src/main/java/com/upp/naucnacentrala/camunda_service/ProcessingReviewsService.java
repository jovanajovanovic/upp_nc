package com.upp.naucnacentrala.camunda_service;


import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Review;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.ReviewRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingReviewsService implements JavaDelegate {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //procesiranje svih recenzija
        System.out.println("==================================================");
        System.out.println("PROCESIRANJE RECENZIJA ");
        Article a = (Article)delegateExecution.getVariable("article");
        Article findA = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());
        List<Review> reviews  = reviewRepository.findByArticle(findA);

        System.out.println("RECENZIJE: ");
        String allComments = "";
        for (Review r : reviews){
            System.out.println(r.getComment());
            allComments += r.getReviewer().getName() + " " + r.getReviewer().getSurname() + " - " + r.getComment() + "\n";
        }

//        reviewRepository.saveAll(reviews);

        delegateExecution.setVariable("all_comments", allComments);

        System.out.println("==================================================");
    }
}
