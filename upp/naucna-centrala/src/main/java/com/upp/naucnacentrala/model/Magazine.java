package com.upp.naucnacentrala.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Magazine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String issn;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScientificField> scientificField;


    @OneToOne
    private Editor chiefEditor;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Reviewer> reviewers;

    @Column
    private MagazineType type;

    @Column
    private boolean activate;

    public Magazine() {
    }



    public Magazine(Long id, String title, String issn, List<ScientificField> scientificField, Editor chiefEditor, Set<Reviewer> reviewers, MagazineType type, boolean activate) {
        this.id = id;
        this.title = title;
        this.issn = issn;
        this.scientificField = scientificField;
        this.chiefEditor = chiefEditor;
        this.reviewers = reviewers;
        this.type = type;
        this.activate = activate;
    }

    public Magazine(String title, String issn, List<ScientificField> scientificField, Editor chiefEditor, Set<Reviewer> reviewers, MagazineType type, boolean activate) {
        this.title = title;
        this.issn = issn;
        this.scientificField = scientificField;
        this.chiefEditor = chiefEditor;
        this.reviewers = reviewers;
        this.type = type;
        this.activate = activate;
    }

    public MagazineType getType() {
        return type;
    }

    public void setType(MagazineType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public List<ScientificField> getScientificField() {
        return scientificField;
    }

    public void setScientificField(List<ScientificField> scientificField) {
        this.scientificField = scientificField;
    }

    public Editor getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(Editor chiefEditor) {
        this.chiefEditor = chiefEditor;
    }



    public Set<Reviewer> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }


    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", issn='" + issn + '\'' +
                ", scientificField=" + scientificField +
                ", chiefEditor=" + chiefEditor +
                ", reviewers=" + reviewers +
                ", type=" + type +
                ", activate=" + activate +
                '}';
    }
}
