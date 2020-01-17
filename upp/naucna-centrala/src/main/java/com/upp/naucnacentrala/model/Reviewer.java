package com.upp.naucnacentrala.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Reviewer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<ScientificField> scientificFieldList;

    public Reviewer() {
    }

    public Reviewer(String name, String surname, String username, String password, String city, String country, String email, String title, boolean activate, Role role, String code, List<ScientificField> scientificFieldList) {
        super(name, surname, username, password, city, country, email, title, activate, role, code);
        this.scientificFieldList = scientificFieldList;
    }
}
