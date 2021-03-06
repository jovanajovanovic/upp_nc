package com.upp.naucnacentrala.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Editor extends User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<ScientificField> scientificFieldList;

    public Editor() {
    }

    public Editor(List<ScientificField> scientificFieldList) {
        this.scientificFieldList = scientificFieldList;
    }

    public Editor(String name, String surname, String username, String password, String city, String country, String email, String title, boolean activate, Role role, String code, List<ScientificField> scientificFieldList) {
        super(name, surname, username, password, city, country, email, title, activate, role, code);
        this.scientificFieldList = scientificFieldList;
    }

    public List<ScientificField> getScientificFieldList() {
        return scientificFieldList;
    }

    public void setScientificFieldList(List<ScientificField> scientificFieldList) {
        this.scientificFieldList = scientificFieldList;
    }
}
