package com.upp.naucnacentrala.dto;

import java.io.Serializable;
import java.util.List;

public class EditBoardDto  implements Serializable {
    private List<String> editors;
    private List<String> reviewers;

    public EditBoardDto(List<String> editors, List<String> reviewers) {
        this.editors = editors;
        this.reviewers = reviewers;
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
    public String    toString() {
        return "EditBoardDto{" +
                "editors=" + editors +
                ", reviewers=" + reviewers +
                '}';
    }
}
