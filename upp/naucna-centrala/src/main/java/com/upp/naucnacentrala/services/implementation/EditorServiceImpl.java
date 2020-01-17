package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.model.Editor;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.EditorRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.services.EditorService;
import com.upp.naucnacentrala.services.ScientificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditorServiceImpl implements EditorService {
    @Autowired
    private ScientificRepository scientificRepository;

    @Autowired
    private EditorRepository editorRepository;

    @Override
    public List<EditorReviewerDto> getEditors(List<String> scientifics) {
        List<ScientificField> scientificFields = getScientificFileds(scientifics);
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

        return editorDtos;
    }


    public List<ScientificField> getScientificFileds(List<String> scientifics){
        List<ScientificField> scientificFields = new ArrayList<>();
        for (String s:scientifics) {
            scientificFields.add(this.scientificRepository.findByName(s));
        }

        return scientificFields;
    }
}
