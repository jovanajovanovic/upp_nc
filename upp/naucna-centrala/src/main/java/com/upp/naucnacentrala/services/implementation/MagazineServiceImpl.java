package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.dto.EditorReviewerDto;
import com.upp.naucnacentrala.dto.MagazineDto;
import com.upp.naucnacentrala.dto.MagazineRegisterDto;
import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.*;
import com.upp.naucnacentrala.repository.EditorRepository;
import com.upp.naucnacentrala.repository.MagazineRepository;
import com.upp.naucnacentrala.repository.ReviewerRepository;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.services.MagazineService;
import com.upp.naucnacentrala.services.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MagazineServiceImpl implements MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private EditorRepository editorRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private ScientificRepository scientificRepository;

    @Override
    public List<Magazine> getAllMagazines() {
        return magazineRepository.findByActivate(true);
    }

    @Override
    public MagazineDto makeMagazineDto(MagazineRegisterDto dto) {
        //pretvorimo dobijeni magazine u dto
        MagazineDto magazineDto = new MagazineDto();
        magazineDto.setName(dto.getName());
        magazineDto.setIssn(dto.getIssn());
        magazineDto.setPayment(dto.getPayment());
        magazineDto.setEditors(dto.getEditors());
        magazineDto.setReviewers(dto.getReviewers());
        magazineDto.setScientific(dto.getScientific());
        //izvucemo glavnog urednika
        Editor chief = this.editorRepository.findByUsername(dto.getChiefEditor());
        magazineDto.setChiefEditor(makeUserDto(chief, new ScientificField()));
        //sad napravimo listu editora
        List<EditorReviewerDto> editors = new ArrayList<>();
        if(dto.getEditors() != null) {
            for (String s : dto.getEditors()) {
                String[] tokens = s.split("-");
                Editor e = this.editorRepository.findByUsername(tokens[0]);
                ScientificField sf = this.scientificRepository.findById(Long.valueOf(tokens[1])).get();
                editors.add(makeUserDto(e, sf));
            }
        }
        magazineDto.setEditorList(editors);
        List<EditorReviewerDto> reviewers = new ArrayList<>();
        if(dto.getReviewers() != null) {
            for (String s : dto.getReviewers()) {
                String[] tokens = s.split("-");
                Reviewer e = this.reviewerRepository.findByUsername(tokens[0]);
                ScientificField sf = this.scientificRepository.findById(Long.valueOf(tokens[1])).get();
                reviewers.add(makeUserDto(e, sf));
            }
        }
        magazineDto.setReviewerList(reviewers);
        return magazineDto;
    }

    @Override
    public Magazine getMagazine(String issn) throws ObjectNotFound {
        Magazine m = magazineRepository.findByIssn(issn).orElseThrow(()-> new ObjectNotFound("Magazine does not exist"));
        return m;
    }

    private EditorReviewerDto makeUserDto(User chief, ScientificField sf) {
        return new EditorReviewerDto("", chief.getName(), chief.getUsername(), chief.getSurname(), chief.getRole(), chief.getEmail(), sf);
    }


}
