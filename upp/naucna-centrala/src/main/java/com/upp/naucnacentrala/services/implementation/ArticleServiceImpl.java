package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.model.Article;
import com.upp.naucnacentrala.repository.ArticleRepository;
import com.upp.naucnacentrala.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article getArticle(String title) {
        return articleRepository.findByTitle(title);
    }
}
