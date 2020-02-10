package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.EditorBoard;
import com.upp.naucnacentrala.model.Magazine;
import com.upp.naucnacentrala.model.ScientificField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorBoardRepository extends CrudRepository<EditorBoard, Long> {

    List<EditorBoard> findByScientificFieldAndMagazine(ScientificField sf, Magazine m);
}
