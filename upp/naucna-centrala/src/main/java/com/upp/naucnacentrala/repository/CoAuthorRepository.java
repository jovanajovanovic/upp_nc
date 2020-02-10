package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.CoAuthor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoAuthorRepository extends CrudRepository<CoAuthor, Long> {
}
