package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.model.Review;
import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveChoiceReviewersService  implements JavaDelegate {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("======================================================");
        System.out.println("SACUVAMO IZABRANE RECENZENTE ");
        Boolean reviewers = (Boolean)delegateExecution.getVariable("reviewers");
        Magazine m = (Magazine)delegateExecution.getVariable("select_magazine");
        Article a = (Article)delegateExecution.getVariable("article");
        List<String> userReviewers = new ArrayList<>();
        if (reviewers){
            System.out.println("POSTOJE RECENZENTI I IZABRANI SU ");
            userReviewers = (List<String>) delegateExecution.getVariable("choice_reviewers");
            //izvucemo korisnike na osnovu selektovanih usera
            for (String s: userReviewers
                 ) {
                System.out.println(s);
            }
        }else {
            userReviewers.add(m.getChiefEditor().getUsername());
        }
        //napravicemo recenzije za unesene recenzente
//        delegateExecution.setVariable("selectReviewers", userReviewers);

        List<Review> reviews = new ArrayList<>();
        Review r;
        Article findA = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());
        //recenzije, napravimo listu da bi u nju dodavali
        for(String username : userReviewers){
            User u = userRepository.findByUsername(username);
            //e sad za ovog usera cemo napraviti recenziju
            r = new Review();
            r.setReviewer(u);
            r.setArticle(a);
            r.setComment("");
            reviews.add(r);
        }
        delegateExecution.setVariable("reviews", reviews);
    }


}
