package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.model.Editor;
import com.upp.naucnacentrala.model.Reviewer;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.ReviewerRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.services.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewerServiceImpl implements ReviewerService {
    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private ScientificRepository scientificRepository;

    @Override
    public List<EditorReviewerDto> getReviewers(List<String> scientifics) {
        List<ScientificField> scientificFields = getScientificFileds(scientifics);
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

    private List<ScientificField> getScientificFileds(List<String> scientifics) {
            List<ScientificField> scientificFields = new ArrayList<>();
            for (String s:scientifics) {
                scientificFields.add(this.scientificRepository.findByName(s));
            }

            return scientificFields;

    }
}
