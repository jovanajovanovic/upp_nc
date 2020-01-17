package com.upp.naucnacentrala.dto;

import com.upp.naucnacentrala.model.Role;
import com.upp.naucnacentrala.model.ScientificField;

import java.io.Serializable;
import java.util.List;

public class EditorReviewerDto implements Serializable {
    private String id;
    private String name;
    private String username;
    private String surname;
    private Role role;
    private String email;
    private ScientificField scientific;

    public EditorReviewerDto(String id, String name, String username, String surname, Role role, String email, ScientificField scientific) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.scientific = scientific;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ScientificField getScientific() {
        return scientific;
    }

    public void setScientific(ScientificField scientific) {
        this.scientific = scientific;
    }
}
