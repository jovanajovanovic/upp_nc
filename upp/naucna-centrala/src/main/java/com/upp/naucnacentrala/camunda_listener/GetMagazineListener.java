package com.upp.naucnacentrala.camunda_listener;

import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.repository.MagazineRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMagazineListener implements ExecutionListener {

    @Autowired
    private MagazineRepository magazineRepository;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("GET ALL MAGAZINE - SERVICE");
        List<Magazine> magazines =  magazineRepository.findByActivate(true);

        delegateExecution.setVariable("magazines", magazines);




    }
}
