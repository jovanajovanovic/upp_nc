package com.upp.naucnacentrala.dto;

import com.upp.naucnacentrala.model.Role;

public class DtoToken {
    private String token;
    private Role role;

    public DtoToken(){

    }

    public DtoToken(String token, Role role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
