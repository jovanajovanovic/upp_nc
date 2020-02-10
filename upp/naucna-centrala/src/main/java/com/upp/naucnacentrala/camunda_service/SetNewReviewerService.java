package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Review;
import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetNewReviewerService implements JavaDelegate {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("==================================================");
        System.out.println("PROMENA RECENZENTA ");
        String trenutni_izvrsilac = (String)delegateExecution.getVariable("izvrsilac");
        System.out.println("RECENZENT KOJI JE ODUSTAO JE " + trenutni_izvrsilac);
        List<Review> reviews = (List<Review>) delegateExecution.getVariable("reviews");
        //zamenimo starog recezenta sa novim
        String new_reviewer = (String)delegateExecution.getVariable("reviewer");
        User novi_recenzent = userRepository.findByUsername(new_reviewer);

        for (Review r : reviews){
            if (r.getReviewer().getUsername().equals(trenutni_izvrsilac)){
                r.setReviewer(novi_recenzent);
            }
        }

        delegateExecution.setVariable("izvrsilac", novi_recenzent.getUsername());


    }
}
