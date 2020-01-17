package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.MagazineRegisterDto;
import com.upp.naucnacentrala.model.*;
import com.upp.naucnacentrala.repository.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddEditorBoardService implements JavaDelegate {
    @Autowired
    private EditorRepository editorRepository;
    @Autowired
    private ScientificRepository scientificRepository;
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private EditorBoardRepository editorBoardRepository;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("SERVICE ADD BORAD");
        MagazineRegisterDto magazine = (MagazineRegisterDto) execution.getVariable("magazine");
        System.out.println("Magazine: " + magazine);
        Magazine findMagazine = magazineRepository.findByTitle(magazine.getName());
        //treba da isparsimo editore i reviewer-e i da popunimo edit board
        List<String> editorsDto = magazine.getEditors();
        makeEditBoard(editorsDto, findMagazine);
        //reviewers
        makeAndSaveReviwers(magazine.getReviewers(), findMagazine);
    }

    private void makeAndSaveReviwers(List<String> reviewers, Magazine findMagazine) {
        Set<Reviewer> reviewersSet = new HashSet<>();
        for (String e: reviewers) {
            //username-(id oblasti)
            String[] tokens = e.split("-");
            Reviewer reviewer = reviewerRepository.findByUsername(tokens[0]);
            ScientificField sf = scientificRepository.findById(Long.valueOf(tokens[1])).get();
            reviewersSet.add(reviewer);
        }
        findMagazine.setReviewers(reviewersSet);
        magazineRepository.save(findMagazine);
    }

    private void makeEditBoard(List<String> editorsDto, Magazine magazine) {
        for (String e: editorsDto) {
            //username-(id oblasti)
            String[] tokens = e.split("-");
            Editor editor = editorRepository.findByUsername(tokens[0]);
            ScientificField sf = scientificRepository.findById(Long.valueOf(tokens[1])).get();
            EditorBoard editorBoard = new EditorBoard(editor, sf, magazine);
            editorBoardRepository.save(editorBoard);

        }

    }
}
