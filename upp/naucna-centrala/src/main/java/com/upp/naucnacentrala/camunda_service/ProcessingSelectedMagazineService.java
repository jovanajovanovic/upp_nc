package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.*;
import com.upp.naucnacentrala.repository.AuthorRepository;
import com.upp.naucnacentrala.repository.MagazineRepository;
import com.upp.naucnacentrala.repository.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingSelectedMagazineService implements JavaDelegate {

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("PROCESSING SELECT MAGAZINE ");
        //treba samo da proverimo da li je magazin open access ili ne
        String issn = (String) delegateExecution.getVariable("magazine");  //issn od izabranog magazina
        Magazine m = magazineRepository.findByIssn(issn).orElseThrow(() -> new ObjectNotFound("Magazine does not exist"));

        Boolean open_access = false;
        if (m.getType() == MagazineType.OPEN_ACCESS){
            open_access = true;
        }

        delegateExecution.setVariable("openAccess", open_access);

        String author_username = (String)delegateExecution.getVariable("starter");
        Author author = authorRepository.findByUsername(author_username);
        delegateExecution.setVariable("author", author);
        delegateExecution.setVariable("select_magazine", m);

        //pronadjemo naucne oblasti od casopisa
        List<ScientificField> scientifics  = m.getScientificField();
        System.out.println("SCIENTIFIC FIELDS");
        for(ScientificField sf : scientifics){
            System.out.println(sf.getName());
            System.out.println("-----------------------------");
        }
        delegateExecution.setVariable("scientifics", scientifics);


    }
}
