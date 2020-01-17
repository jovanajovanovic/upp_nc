package com.upp.naucnacentrala.dto;

import java.io.Serializable;

public class AcceptReviewerDto implements Serializable {

    private boolean status;

    public AcceptReviewerDto() {
    }

    public AcceptReviewerDto(boolean status) {
        this();
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
