package com.upp.naucnacentrala.camunda_service;


import com.upp.naucnacentrala.model.Review;
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

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //procesiranje svih recenzija
        System.out.println("PROCESIRANJE RECENZIJA ");
        List<Review> reviews  = (List<Review>) delegateExecution.getVariable("reviews");

        System.out.println("RECENZIJE: ");
        String allComments = "";
        for (Review r : reviews){
            System.out.println(r.getComment());
            allComments += r.getReviewer().getName() + " " + r.getReviewer().getSurname() + " - " + r.getComment() + "\n";
        }

        reviewRepository.saveAll(reviews);

        delegateExecution.setVariable("all_comments", allComments);
    }
}
