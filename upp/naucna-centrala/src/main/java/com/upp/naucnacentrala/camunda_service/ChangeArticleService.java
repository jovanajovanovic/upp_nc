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
public class ChangeArticleService implements JavaDelegate {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ScientificRepository scientificRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("IZMENE CLANKA");
        Article a = (Article)delegateExecution.getVariable("article"); //clanak koji se menja

        String newPdf = (String)delegateExecution.getVariable("new_pdf1");
        System.out.println("STARI CLANAK :" + a.getTitle() + " " + a.getMagazine().getTitle());
        delegateExecution.setVariable("pdf", newPdf);

//        ScientificField newSf = scientificRepository.findByCode(newScientific).orElseThrow(() -> new ObjectNotFound("Scientific field with code " + newScientific + " does not exist"));
        Article changeArticle = articleRepository.findByTitleAndMagazine(a.getTitle(), a.getMagazine());
        System.out.println("STARI CLANAK " + changeArticle.getTitle());
        byte[] file = (byte[]) delegateExecution.getVariable("file");
        changeArticle.setFile(file);
        changeArticle.setPdf(newPdf);
        System.out.println("NOVI CLANAK " + changeArticle.getTitle());
        articleRepository.save(changeArticle);
        delegateExecution.setVariable("article", changeArticle);

        String typeChange  = (String)delegateExecution.getVariable("typeChange");
        Boolean bigChanges = false;
        if (typeChange.equals("big_change")){
            bigChanges = true;
        }
        System.out.println("TIP PROMENA: " + bigChanges);
        delegateExecution.setVariable("bigChanges", bigChanges);

//        delegateExecution.setVariable("select_scientific", newSf.getName());


    }
}
