package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Editor;
import com.upp.naucnacentrala.model.ScientificField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorRepository extends CrudRepository<Editor, Long> {

    Editor findByUsername(String username);

    List<Editor> findByScientificFieldList(List<ScientificField> scientificFields);
}
