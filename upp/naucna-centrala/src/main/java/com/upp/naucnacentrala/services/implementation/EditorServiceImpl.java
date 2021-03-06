package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.Editor;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.EditorRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.services.EditorService;
import com.upp.naucnacentrala.services.ScientificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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

        for (EditorReviewerDto e:editorDtos
             ) {
            System.out.println(e);

        }

        return editorDtos;
    }

    @Override
    public List<String> getEditorsByCode(Object value) throws ObjectNotFound {
        ArrayList<String> keyEditors = (ArrayList<String>) value;
        //prodjemo kroz sve sifre i splitujemo ih po - ;
        List<String> result = new ArrayList<>();
        Editor e;
        ScientificField sf;
        String res;
        for (String s : keyEditors){
            String[] tokens = s.split("-");
            //pronadjemo ime i prezime editora
            e = this.editorRepository.findByUsername(tokens[0].trim());
            sf = this.scientificRepository.findById(Long.parseLong(tokens[1].trim())).orElseThrow(() -> new ObjectNotFound("Scientific field does not exist"));
            res = e.getName() + " " + e.getSurname() + " - " + sf.getName();
            result.add(res);
        }
        return result;
    }


    public List<ScientificField> getScientificFileds(List<String> scientifics){
        List<ScientificField> scientificFields = new ArrayList<>();
        for (String s:scientifics) {
            scientificFields.add(this.scientificRepository.findByName(s));
        }

        return scientificFields;
    }
}
