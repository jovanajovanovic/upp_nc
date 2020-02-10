package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Magazine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    Article findByTitleAndMagazine(String title, Magazine m);

    Article findByTitle(String title);
}
