package com.upp.naucnacentrala.dto;

import java.io.Serializable;

public class ActivateMagazineDto implements Serializable {

    private boolean activate;

    public ActivateMagazineDto() {
    }

    public ActivateMagazineDto(boolean activate) {
        this.activate = activate;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
}
