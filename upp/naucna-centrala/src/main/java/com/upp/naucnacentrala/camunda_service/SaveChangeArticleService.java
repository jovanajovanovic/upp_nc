package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SaveChangeArticleService implements JavaDelegate {
    @Autowired
    private ScientificRepository scientificRepository;

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("IZMENA CLANKA");
        Article a = (Article)delegateExecution.getVariable("article"); //clanak koji se menja

        String newTitle = (String)delegateExecution.getVariable("title");
        String newApstract = (String)delegateExecution.getVariable("apstract");
        String newScientific = (String)delegateExecution.getVariable("scientific_new");
        String newKeyWords = (String)delegateExecution.getVariable("key_word");
        String newPdf = (String)delegateExecution.getVariable("pdf");

        ScientificField newSf = scientificRepository.findByCode(newScientific).orElseThrow(() -> new ObjectNotFound("Scientific field with code " + newScientific + " does not exist"));
        Article changeArticle = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());
        System.out.println("STARI CLANAK " + changeArticle.getTitle());
        changeArticle.setTitle(newTitle);
        changeArticle.setApstract(newApstract);
        changeArticle.setKeyWords(newKeyWords);
        changeArticle.setPdf(newPdf);
        changeArticle.setScientific(newSf);
        System.out.println("NOVI CLANAK " + changeArticle.getTitle());
        articleRepository.save(changeArticle);
        delegateExecution.setVariable("article", changeArticle);

    }
}
