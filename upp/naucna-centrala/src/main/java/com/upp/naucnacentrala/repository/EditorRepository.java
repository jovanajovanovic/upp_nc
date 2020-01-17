package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Editor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorRepository extends CrudRepository<Editor, Long> {

    Editor findByUsername(String username);
}
