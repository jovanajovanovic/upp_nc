package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.dto.MagazineRegisterDto;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.repository.MagazineRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivateMagazineService implements JavaDelegate {

    @Autowired
    private MagazineRepository magazineRepository;


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("SERVICE ACTIVATE BORAD");
        Magazine magazine = (Magazine) execution.getVariable("magazine");
        System.out.println("Magazine: " + magazine);
        Magazine findMagazine = magazineRepository.findByTitle(magazine.getTitle());

        boolean activate = (Boolean)execution.getVariable("activate");
        findMagazine.setActivate(activate);
        magazineRepository.save(findMagazine);
    }
}
