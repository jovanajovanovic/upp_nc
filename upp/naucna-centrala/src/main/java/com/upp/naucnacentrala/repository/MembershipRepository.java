package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.model.MembershipFees;
import com.upp.naucnacentrala.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<MembershipFees, Long> {

    MembershipFees findByMagazineAndUser(Magazine m, User u);
}
