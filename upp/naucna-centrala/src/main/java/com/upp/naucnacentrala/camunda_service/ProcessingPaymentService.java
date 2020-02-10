package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.model.Author;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.model.MembershipFees;
import com.upp.naucnacentrala.repository.MembershipRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessingPaymentService implements JavaDelegate {

    @Autowired
    private MembershipRepository membershipRepository;




    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("PAYMENT SERVICE");
        Magazine m = (Magazine)delegateExecution.getVariable("select_magazine");
        Author author = (Author)delegateExecution.getVariable("author");

        Boolean payment = (Boolean) delegateExecution.getVariable("payment");

        if (payment){
            MembershipFees mf = new MembershipFees(m, author);
            membershipRepository.save(mf);
        }
    }
}
