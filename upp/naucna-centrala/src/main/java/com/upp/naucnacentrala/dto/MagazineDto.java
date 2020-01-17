package com.upp.naucnacentrala.dto;

import com.upp.naucnacentrala.model.Magazine;

import java.io.Serializable;
import java.util.List;

public class MagazineDto implements Serializable {
    private String name;
    private String issn;
    private String payment;
    private List<String> scientific;
    private EditorReviewerDto chiefEditor;
    private List<String> editors;
    private  List<String> reviewers;
    private List<EditorReviewerDto> editorList;
    private List<EditorReviewerDto> reviewerList;

    public MagazineDto(){

    }
    public MagazineDto(String name, String issn, String payment, List<String> scientific, EditorReviewerDto chiefEditor, List<String> editors, List<String> reviewers, List<EditorReviewerDto> editorList, List<EditorReviewerDto> reviewerList) {
        this.name = name;
        this.issn = issn;
        this.payment = payment;
        this.scientific = scientific;
        this.chiefEditor = chiefEditor;
        this.editors = editors;
        this.reviewers = reviewers;
        this.editorList = editorList;
        this.reviewerList = reviewerList;
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

    public EditorReviewerDto getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(EditorReviewerDto chiefEditor) {
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

    public List<EditorReviewerDto> getEditorList() {
        return editorList;
    }

    public void setEditorList(List<EditorReviewerDto> editorList) {
        this.editorList = editorList;
    }

    public List<EditorReviewerDto> getReviewerList() {
        return reviewerList;
    }

    public void setReviewerList(List<EditorReviewerDto> reviewerList) {
        this.reviewerList = reviewerList;
    }
}
