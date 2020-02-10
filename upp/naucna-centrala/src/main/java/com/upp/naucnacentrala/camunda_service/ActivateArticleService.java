package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.repository.ArticleRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivateArticleService implements JavaDelegate {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("AKTIVACIJA CASOPISA");
        Article article = (Article)delegateExecution.getVariable("article");
        System.out.println(article.getTitle());

        Article a = articleRepository.findByTitleAndMagazine(article.getTitle(), article.getMagazine());
        a.setAccept(true);
        articleRepository.save(a);




    }
}
