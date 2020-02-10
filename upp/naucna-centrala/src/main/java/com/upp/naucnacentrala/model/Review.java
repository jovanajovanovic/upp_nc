package com.upp.naucnacentrala.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn
    private Article article;

    @ManyToOne
    @JoinColumn
    private User reviewer;

    public Review() {
    }

    public Review(String comment, Article article, User reviewer) {
        this.comment = comment;
        this.article = article;
        this.reviewer = reviewer;
    }

    public Review(Long id, String comment, Article article, User reviewer) {
        this.comment = comment;
        this.article = article;
        this.reviewer = reviewer;
        this.id =id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }


    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", article=" + article +
                ", reviewer=" + reviewer.getName() +
                '}';
    }
}
