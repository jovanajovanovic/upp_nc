package com.upp.naucnacentrala.model;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String apstract;

    @Column(nullable = false)
    private String keyWords;

    //jedna, veza ce da bude da jedan casopis pripada 1 naucnoj oblasti, a naucna oblast moze da ima vise casopisa
    @OneToOne
    private ScientificField scientific;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<CoAuthor> coAuthors;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<Review> reviewes;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Editor editor;

    @ManyToOne
    @JoinColumn
    private  Magazine magazine;

    @Column(nullable = false)
    private String pdf;

    @Column(nullable = false)
    private boolean accept;

    @Column
    private String doi;

    @Column
    private boolean reject;

    @Lob
    @Column(length = 10000)
    private byte[] file;


    public Article() {
    }

    public Article(String title, String apstract, String keyWords, ScientificField scientific, List<CoAuthor> coAuthors, Author author, Magazine magazine, String pdf, boolean accept) {
        this.title = title;
        this.apstract = apstract;
        this.keyWords = keyWords;
        this.scientific = scientific;
        this.coAuthors = coAuthors;
        this.author = author;
        this.magazine = magazine;
        this.pdf = pdf;
        this.accept = accept;
    }

    public Article(String title, String apstract, String keyWords, ScientificField scientific, Author author, Magazine magazine, String pdf, boolean accept) {
        this.title = title;
        this.apstract = apstract;
        this.keyWords = keyWords;
        this.scientific = scientific;
        this.author = author;
        this.magazine = magazine;
        this.pdf = pdf;
        this.accept = accept;
    }

    public Article(String title, String apstract, String keyWords, ScientificField scientific, List<CoAuthor> coAuthors, Set<Review> reviewes, Author author, Editor editor, Magazine magazine, String pdf, boolean accept) {
        this.title = title;
        this.apstract = apstract;
        this.keyWords = keyWords;
        this.scientific = scientific;
        this.coAuthors = coAuthors;
        this.reviewes = reviewes;
        this.author = author;
        this.editor = editor;
        this.magazine = magazine;
        this.pdf = pdf;
        this.accept = accept;
    }

    public Article(String title, String apstract, String keyWords, ScientificField scientific, List<CoAuthor> coAuthors, Set<Review> reviewes, Author author, Editor editor, Magazine magazine, String pdf, boolean accept, String doi) {
        this.title = title;
        this.apstract = apstract;
        this.keyWords = keyWords;
        this.scientific = scientific;
        this.coAuthors = coAuthors;
        this.reviewes = reviewes;
        this.author = author;
        this.editor = editor;
        this.magazine = magazine;
        this.pdf = pdf;
        this.accept = accept;
        this.doi = doi;
    }


    public Article(String title, String apstract, String keyWords, ScientificField scientific, List<CoAuthor> coAuthors, Set<Review> reviewes, Author author, Editor editor, Magazine magazine, String pdf, boolean accept, String doi, boolean reject) {
        this.title = title;
        this.apstract = apstract;
        this.keyWords = keyWords;
        this.scientific = scientific;
        this.coAuthors = coAuthors;
        this.reviewes = reviewes;
        this.author = author;
        this.editor = editor;
        this.magazine = magazine;
        this.pdf = pdf;
        this.accept = accept;
        this.doi = doi;
        this.reject = reject;
    }

    public Article(String title, String apstract, String keyWords, ScientificField scientific, List<CoAuthor> coAuthors, Set<Review> reviewes, Author author, Editor editor, Magazine magazine, String pdf, boolean accept, String doi, boolean reject, byte[] file) {
        this.title = title;
        this.apstract = apstract;
        this.keyWords = keyWords;
        this.scientific = scientific;
        this.coAuthors = coAuthors;
        this.reviewes = reviewes;
        this.author = author;
        this.editor = editor;
        this.magazine = magazine;
        this.pdf = pdf;
        this.accept = accept;
        this.doi = doi;
        this.reject = reject;
        this.file = file;
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

    public String getApstract() {
        return apstract;
    }

    public void setApstract(String apstract) {
        this.apstract = apstract;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public ScientificField getScientific() {
        return scientific;
    }

    public void setScientific(ScientificField scientific) {
        this.scientific = scientific;
    }

    public List<CoAuthor> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(List<CoAuthor> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public Set<Review> getReviewes() {
        return reviewes;
    }

    public void setReviewes(Set<Review> reviewes) {
        this.reviewes = reviewes;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public boolean isReject() {
        return reject;
    }

    public void setReject(boolean reject) {
        this.reject = reject;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", apstract='" + apstract + '\'' +
                ", keyWords='" + keyWords + '\'' +
                ", scientific=" + scientific +
                ", reviewes=" + reviewes +
                ", author=" + author +
                ", editor=" + editor +
                ", magazine=" + magazine +
                ", pdf='" + pdf + '\'' +
                ", accept=" + accept +
                ", doi='" + doi + '\'' +
                '}';
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}

