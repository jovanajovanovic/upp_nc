package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByUsername(String username);
}
