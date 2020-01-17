package com.upp.naucnacentrala.dto;

import java.io.Serializable;

public class CheckDataDto implements Serializable {

    private boolean ok;

    public CheckDataDto() {
    }

    public CheckDataDto(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
