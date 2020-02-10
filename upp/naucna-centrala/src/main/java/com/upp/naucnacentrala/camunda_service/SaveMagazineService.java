package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.dto.MagazineRegisterDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.*;
import com.upp.naucnacentrala.repository.EditorRepository;
import com.upp.naucnacentrala.repository.MagazineRepository;
import com.upp.naucnacentrala.repository.ReviewerRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.services.EditorService;
import com.upp.naucnacentrala.services.ReviewerService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class SaveMagazineService implements JavaDelegate {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private EditorRepository editorRepository;

    @Autowired
    private ScientificRepository scientificRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println(">> SAVE MAGAZINE SERVICE >>");

        //uzmemo sve promenljive od kojih pravimo magazin
        String name = (String) execution.getVariable("name");
        String issn = (String) execution.getVariable("issn");
        String payment1 = (String) execution.getVariable("payment");
        MagazineType payment = MagazineType.valueOf(payment1);
        List<String> scientifics = (List<String>) execution.getVariable("scientific");
        String chiefEditor = (String) execution.getVariable("starter");


        Magazine newMagazine = magazineRepository.findByTitle(name);

        System.out.println(scientifics);
        //dodamo novi casopis
        Editor editor = this.editorRepository.findByUsername(chiefEditor);
        List<ScientificField> scientificFields = getScientificFields(scientifics);

        if(newMagazine != null){
            newMagazine.setTitle(name);
            newMagazine.setIssn(issn);
            newMagazine.setScientificField(scientificFields);
            newMagazine.setType(payment);
        }else {
            newMagazine = new Magazine(name, issn, scientificFields, editor,
                    new HashSet<>(), payment, false);
        }
        magazineRepository.save(newMagazine);

        execution.setVariable("magazine", newMagazine);
        execution.setVariable("chief", editor.getName() + " " + editor.getSurname());

        //treba jos da izvucemo sve editore za magazine i sve recenzente
        List<EditorReviewerDto> editors = getEditors(scientificFields);
        for (EditorReviewerDto e : editors) {
            System.out.println(e);
        }
        List<EditorReviewerDto> reviewers = getReviewers(scientificFields);

        execution.setVariable("all_editors", editors);
        execution.setVariable("all_reviewers", reviewers);


    }


    private List<ScientificField> getScientificFields(List<String> scientific) throws ObjectNotFound {
        List<ScientificField> scientificFields = new ArrayList<>();
        for (String s : scientific) {
            scientificFields.add(this.scientificRepository.findByCode(s).orElseThrow(()-> new ObjectNotFound("Scientific field does not exist!")));
        }
        return scientificFields;
    }

    public List<EditorReviewerDto> getEditors(List<ScientificField> scientificFields) {
      //  List<ScientificField> scientificFields = getScientificFileds(scientifics);
        List<Editor> allEditors = new ArrayList<>();
        List<EditorReviewerDto> editorDtos =  new ArrayList<>();
        for (ScientificField sf: scientificFields) {
            List<ScientificField> scientificFields1 = new ArrayList<>();
            scientificFields1.add(sf);
            List<Editor> editors = this.editorRepository.findByScientificFieldList(scientificFields1);
            for (Editor e: editors) {
                String id = e.getUsername()+"-"+sf.getId();
                editorDtos.add(new EditorReviewerDto(id, e.getName(), e.getUsername(), e.getSurname(), e.getRole(), e.getEmail(), sf));
            }
        }

        for (EditorReviewerDto e:editorDtos
        ) {
            System.out.println(e);

        }

        return editorDtos;
    }

    public List<EditorReviewerDto> getReviewers(List<ScientificField> scientificFields) {
//        List<ScientificField> scientificFields = getScientificFileds(scientifics);
        List<Reviewer> allEditors = new ArrayList<>();
        List<EditorReviewerDto> editorDtos =  new ArrayList<>();
        for (ScientificField sf: scientificFields) {
            List<ScientificField> scientificFields1 = new ArrayList<>();
            scientificFields1.add(sf);
            List<Reviewer> reviewers = this.reviewerRepository.findByScientificFieldList(scientificFields1);
            for (Reviewer e: reviewers) {
                String id = e.getUsername()+"-"+sf.getId();
                editorDtos.add(new EditorReviewerDto(id, e.getName(), e.getUsername(), e.getSurname(), e.getRole(), e.getEmail(), sf));
            }
        }

        return editorDtos;
    }
}
