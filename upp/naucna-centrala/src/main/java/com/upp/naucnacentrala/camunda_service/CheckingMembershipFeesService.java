package com.upp.naucnacentrala.camunda_service;

import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.Author;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.model.MembershipFees;
import com.upp.naucnacentrala.repository.MagazineRepository;
import com.upp.naucnacentrala.repository.MembershipRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingMembershipFeesService implements JavaDelegate {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private MagazineRepository magazineRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //treba da proverimo da li autor ima placenu clanarinu u casopisu
        Author author = (Author) delegateExecution.getVariable("author");
        String issn = (String)delegateExecution.getVariable("magazine");
        System.out.println("CHECKING MEMBERSHIP FOR AUTHOR " + author.getUsername() + "  IN MAGAZINE " + issn);

        Magazine m = magazineRepository.findByIssn(issn).orElseThrow(()-> new ObjectNotFound("Magazine does not exist"));

        Boolean pay = false;
        MembershipFees mf = membershipRepository.findByMagazineAndUser(m, author);
        if (mf != null){
            pay = true;
        }

        delegateExecution.setVariable("fee", pay);
//        delegateExecution.setVariable("magazine_name", m.getTitle());

    }
}
