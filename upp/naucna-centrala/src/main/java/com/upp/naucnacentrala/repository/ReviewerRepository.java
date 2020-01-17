package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends CrudRepository<Reviewer, Long> {

}
