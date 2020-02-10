package com.upp.naucnacentrala.model;

import javax.persistence.*;

@Entity
public class EditorBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne
    private Editor editor;

    @OneToOne
    private ScientificField scientificField;

    @ManyToOne
    @JoinColumn
    private Magazine magazine;

    public EditorBoard() {
    }

    public EditorBoard(Editor editor, ScientificField scientificField, Magazine magazine) {
        this.editor = editor;
        this.scientificField = scientificField;
        this.magazine = magazine;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }
}
