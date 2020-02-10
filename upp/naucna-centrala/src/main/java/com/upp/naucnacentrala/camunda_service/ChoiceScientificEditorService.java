package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.*;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.repository.EditorBoardRepository;
import com.upp.naucnacentrala.repository.MagazineRepository;
import com.upp.naucnacentrala.repository.ReviewerRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ChoiceScientificEditorService implements JavaDelegate {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private EditorBoardRepository editorBoardRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //biramo urednika naucne oblasti za koju je rad poslat
        System.out.println("IZBOR UREDNIKA NAUCNE OBLASTI ");

        Article a  = (Article) delegateExecution.getVariable("article");
        Magazine m = (Magazine)delegateExecution.getVariable("select_magazine");

        ScientificField choiceScientific = a.getScientific();
        System.out.println("IZABRANA NAUCNA OBLAST " + a.getScientific());

        Magazine selectMagazine = magazineRepository.findByIssn(m.getIssn()).orElseThrow(() -> new ObjectNotFound("Magazine with issn " + m.getIssn() + " does not exists"));

        //trazimo edit board koji u sebi ima magazin i naucnu oblast
        List<EditorBoard> editorBoards = editorBoardRepository.findByScientificFieldAndMagazine(choiceScientific, selectMagazine);

        String sci_editor;
        if(editorBoards.size() == 0){
            //nemamo urednika za trazenu oblast
            System.out.println("Nemamo urednika za naucnu oblast " + choiceScientific.getName());
            sci_editor = m.getChiefEditor().getUsername();
            System.out.println("UZELI SMO GLAVNOG UREDNIKA " + sci_editor);
        }else {
            System.out.println("POSTOJI UREDNIK ZA NAUCNU OBLAST");
            sci_editor = editorBoards.get(0).getEditor().getUsername();
            System.out.println("UZELI SMO UREDNIKA NAUCNE OBLASTI " + sci_editor);
        }

        delegateExecution.setVariable("sci_editor", sci_editor);

        getReviewers(delegateExecution, a.getScientific(), selectMagazine);

    }

    private void  getReviewers(DelegateExecution delegateExecution, ScientificField sf, Magazine m){
        //ovde cemo da prikupimo sve recenzente naucne oblasti
        Set<Reviewer> reviewers = m.getReviewers();
        //prodjemo kroz set i pogledamo da li imamo recenzente za trazenu oblast
        List<Reviewer> reviewersDto = new ArrayList<>();
        for (Reviewer r : reviewers){
            if (r.getScientificFieldList().contains(sf)){
                reviewersDto.add(r);
            }
        }

        System.out.println("recenzenti: ");
        for (Reviewer s: reviewersDto
             ) {
            System.out.println(s.getUsername());
        }

        delegateExecution.setVariable("reviewers_dto", reviewersDto); //za prikaz recenzenata koje ce da bira

        Boolean found_r = true;
        if(reviewersDto.size() == 0){
            found_r = false;
        }
        delegateExecution.setVariable("reviewers", found_r); //da znamo da li imamo recenzenata ili ne
    }
}
