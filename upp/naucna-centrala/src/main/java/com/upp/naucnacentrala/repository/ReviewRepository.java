package com.upp.naucnacentrala.repository;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByArticle(Article a);
}
