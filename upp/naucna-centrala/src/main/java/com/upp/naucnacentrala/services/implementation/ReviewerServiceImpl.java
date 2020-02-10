package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
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

    @Override
    public List<String> getReviewersByCode(Object value) throws ObjectNotFound {
        ArrayList<String> keyEditors = (ArrayList<String>) value;
        //prodjemo kroz sve sifre i splitujemo ih po - ;
        List<String> result = new ArrayList<>();
        Reviewer r;
        ScientificField sf;
        String res;
        for (String s : keyEditors){
            String[] tokens = s.split("-");
            //pronadjemo ime i prezime editora
            r = this.reviewerRepository.findByUsername(tokens[0].trim());
            sf = this.scientificRepository.findById(Long.parseLong(tokens[1].trim())).orElseThrow(() -> new ObjectNotFound("Scientific field does not exist"));
            res = r.getName() + " " + r.getSurname() + " - " + sf.getName();
            result.add(res);
        }
        return result;
    }

    private List<ScientificField> getScientificFileds(List<String> scientifics) {
            List<ScientificField> scientificFields = new ArrayList<>();
            for (String s:scientifics) {
                scientificFields.add(this.scientificRepository.findByName(s));
            }

            return scientificFields;

    }
}
