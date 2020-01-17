package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Reviewer;
import com.upp.naucnacentrala.model.ScientificField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewerRepository extends CrudRepository<Reviewer, Long> {

    List<Reviewer> findByScientificFieldList(List<ScientificField> scientifics);

    Reviewer findByUsername(String username);
}
