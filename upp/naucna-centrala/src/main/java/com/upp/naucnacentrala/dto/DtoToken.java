package com.upp.naucnacentrala.dto;

public class DtoToken {
    private String token;

    public DtoToken(){

    }
    public DtoToken(String token) {
        this();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
