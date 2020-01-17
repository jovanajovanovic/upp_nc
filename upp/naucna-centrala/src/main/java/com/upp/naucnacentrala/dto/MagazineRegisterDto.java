package com.upp.naucnacentrala.dto;

import com.upp.naucnacentrala.model.MagazineType;

import java.io.Serializable;
import java.util.List;

public class MagazineRegisterDto implements Serializable {

    private String name;
    private String issn;
    private String payment;
    private List<String> scientific;
    private String chiefEditor;
    private List<String> editors;
    private  List<String> reviewers;

    public MagazineRegisterDto(String name, String issn, String payment, List<String> scientific, String chiefEditor, List<String> editors, List<String> reviewers) {
        this.name = name;
        this.issn = issn;
        this.payment = payment;
        this.scientific = scientific;
        this.chiefEditor = chiefEditor;
        this.editors = editors;
        this.reviewers = reviewers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<String> getScientific() {
        return scientific;
    }

    public void setScientific(List<String> scientific) {
        this.scientific = scientific;
    }

    public String getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(String chiefEditor) {
        this.chiefEditor = chiefEditor;
    }

    public List<String> getEditors() {
        return editors;
    }

    public void setEditors(List<String> editors) {
        this.editors = editors;
    }

    public List<String> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<String> reviewers) {
        this.reviewers = reviewers;
    }

    @Override
    public String toString() {
        return "MagazineRegisterDto{" +
                "name='" + name + '\'' +
                ", issn='" + issn + '\'' +
                ", payment='" + payment + '\'' +
                ", scientific=" + scientific +
                ", chiefEditor='" + chiefEditor + '\'' +
                ", editors=" + editors +
                ", reviewers=" + reviewers +
                '}';
    }
}
