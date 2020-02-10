package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.ScientificField;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ScientificRepository extends CrudRepository<ScientificField, Long> {

    ScientificField findByName(String name);
    Optional<ScientificField> findByCode(String code);
}
