package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Magazine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends CrudRepository<Magazine, Long> {
}
