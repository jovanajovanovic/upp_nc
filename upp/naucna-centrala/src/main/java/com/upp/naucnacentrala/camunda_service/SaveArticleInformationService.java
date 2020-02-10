package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.*;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveArticleInformationService implements JavaDelegate {
    @Autowired
    private ScientificRepository scientificRepository;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("SAVE ARTICLE INFORMATION");
        String title = (String)delegateExecution.getVariable("title");
        String scientific_code = (String) delegateExecution.getVariable("scientific");
        String apstract = (String) delegateExecution.getVariable("apstract");
        String key_word = (String)delegateExecution.getVariable("key_word");
        String pdf = (String)delegateExecution.getVariable("pdf");
        byte[] file = (byte[]) delegateExecution.getVariable("file");

        Author a = (Author) delegateExecution.getVariable("author");
        Magazine m = (Magazine) delegateExecution.getVariable("select_magazine");

        ScientificField sf = scientificRepository.findByCode(scientific_code).orElseThrow(()-> new ObjectNotFound("Scientific field with code: "+ scientific_code +" does not exist"));

        Article newArticle = new Article(title, apstract, key_word, sf, a, m, pdf, false);
      //  articleRepository.save(newArticle);
        newArticle.setFile(file);
        newArticle.setReject(false);

        delegateExecution.setVariable("article", newArticle);
        List<CoAuthor> coauthors = new ArrayList<>();
        System.out.println("USPESNO SACUVAN CLANAK " + newArticle);
        delegateExecution.setVariable("coauthors", coauthors);
        delegateExecution.setVariable("select_scientific", sf.getName());

    }
}
