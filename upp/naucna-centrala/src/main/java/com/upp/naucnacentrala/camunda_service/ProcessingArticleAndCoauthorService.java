package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.CoAuthor;
import com.upp.naucnacentrala.model.Editor;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.AuthorRepository;
import com.upp.naucnacentrala.repository.CoAuthorRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingArticleAndCoauthorService implements JavaDelegate {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CoAuthorRepository coAuthorRepository;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("CUVANJE CLANKA I KOAUTORA U BAZI ");
        Article a = (Article) delegateExecution.getVariable("article");
        Magazine m = (Magazine) delegateExecution.getVariable("select_magazine");
        Editor chiefEditor = m.getChiefEditor();
        a.setEditor(chiefEditor);
        this.articleRepository.save(a);
        System.out.println("ARTICLE: " + a);
        List<CoAuthor> coAuthors = (List<CoAuthor>) delegateExecution.getVariable("coauthors");
        for(CoAuthor ca : coAuthors) {
            ca.setArticle(a);
            System.out.println("KOAUTHOR: " + ca);
        }

        coAuthorRepository.saveAll(coAuthors);
        delegateExecution.setVariable("article", a);
        delegateExecution.setVariable("chiefEditor", chiefEditor.getUsername());



    }
}
