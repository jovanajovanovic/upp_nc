package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.repository.ArticleRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectArticleService implements JavaDelegate {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("REJECT ARTICLE ");

        Article a = (Article)delegateExecution.getVariable("article");
        a.setReject(true);
        articleRepository.save(a);

    }
}
