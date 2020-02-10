package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Magazine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MagazineRepository extends CrudRepository<Magazine, Long> {
    Magazine findByTitle(String name);

    List<Magazine> findByActivate(boolean activate);

    Optional<Magazine> findByIssn(String issn);
}
