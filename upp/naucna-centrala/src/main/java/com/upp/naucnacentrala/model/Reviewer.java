package com.upp.naucnacentrala.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Reviewer extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<ScientificField> scientificFieldList;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    public Reviewer() {
    }

    public Reviewer(String name, String surname, String username, String password, String city, String country, String email, String title, boolean activate, Role role, String code, List<ScientificField> scientificFieldList) {
        super(name, surname, username, password, city, country, email, title, activate, role, code);
        this.scientificFieldList = scientificFieldList;
    }


    public Reviewer(String name, String surname, String username, String password, String city, String country, String email, String title, boolean activate, Role role, String code, List<ScientificField> scientificFieldList, Set<Review> reviews) {
        super(name, surname, username, password, city, country, email, title, activate, role, code);
        this.scientificFieldList = scientificFieldList;
        this.reviews = reviews;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<ScientificField> getScientificFieldList() {
        return scientificFieldList;
    }

    public void setScientificFieldList(List<ScientificField> scientificFieldList) {
        this.scientificFieldList = scientificFieldList;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
