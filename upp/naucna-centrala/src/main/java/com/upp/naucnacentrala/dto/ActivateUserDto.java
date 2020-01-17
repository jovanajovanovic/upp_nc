package com.upp.naucnacentrala.dto;

import java.io.Serializable;

public class ActivateUserDto implements Serializable {

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
