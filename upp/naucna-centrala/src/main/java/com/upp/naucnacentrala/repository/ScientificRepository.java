package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.ScientificField;
import org.springframework.data.repository.CrudRepository;


public interface ScientificRepository extends CrudRepository<ScientificField, Long> {

    ScientificField findByName(String name);
}
