package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.repository.ArticleRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DOIService implements JavaDelegate {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("DOI SERVICE");
        Article a = (Article)delegateExecution.getVariable("article");

        Article article = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());

        int length = 8;
        boolean useLetters = true;
        boolean useNumber = true;
        String doi = RandomStringUtils.random(length, useLetters, useNumber);

        article.setDoi(doi);
        articleRepository.save(article);


    }
}
