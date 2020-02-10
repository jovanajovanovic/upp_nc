package com.upp.naucnacentrala.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //jedan moze da objavi vise radova, a rad pripada jednom glavnom autoru
   @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Article> articles;

    public Author() {
    }

    public Author(String name, String surname, String username, String password, String city, String country, String email, String title, boolean activate, Role role, String code) {
        super(name, surname, username, password, city, country, email, title, activate, role, code);
    }

    public Author(Set<Article> articles) {
        this.articles = articles;
    }

    public Author(String name, String surname, String username, String password, String city, String country, String email, String title, boolean activate, Role role, String code, Set<Article> articles) {
        super(name, surname, username, password, city, country, email, title, activate, role, code);
        this.articles = articles;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
