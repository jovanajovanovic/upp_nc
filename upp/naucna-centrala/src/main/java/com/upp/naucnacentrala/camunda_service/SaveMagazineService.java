package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.MagazineRegisterDto;
import com.upp.naucnacentrala.dto.RegisterUserDto;
import com.upp.naucnacentrala.model.Editor;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.model.MagazineType;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.EditorRepository;
import com.upp.naucnacentrala.repository.MagazineRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println(">> SAVE MAGAZINE SERVICE >>");
        MagazineRegisterDto magazine = (MagazineRegisterDto) execution.getVariable("magazine");
        System.out.println(magazine);

        //dodamo novi casopis
        Editor chiefEditor = this.editorRepository.findByUsername(magazine.getChiefEditor());
        List<ScientificField> scientificFields = getScientificFields(magazine.getScientific());

        Magazine newMagazine = new Magazine(magazine.getName(), magazine.getIssn(), scientificFields, chiefEditor, new HashMap<>(),
                new HashSet<>(),MagazineType.valueOf(magazine.getPayment()) , false);

        magazineRepository.save(newMagazine);

    }


    private List<ScientificField> getScientificFields(List<String> scientific) {
        List<ScientificField> scientificFields = new ArrayList<>();
        for (String s : scientific) {
            scientificFields.add(this.scientificRepository.findByName(s));
        }
        return scientificFields;
    }
}
