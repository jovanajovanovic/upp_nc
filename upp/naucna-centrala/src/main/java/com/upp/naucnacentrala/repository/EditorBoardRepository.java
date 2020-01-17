package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.EditorBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorBoardRepository extends CrudRepository<EditorBoard, Long> {
}
