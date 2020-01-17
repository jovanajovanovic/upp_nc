package com.upp.naucnacentrala.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Editor extends User {
    public Editor() {
    }

    public Editor(String name, String surname, String username, String password, String city, String country, String email, String title, boolean activate, Role role, String code) {
        super(name, surname, username, password, city, country, email, title, activate, role, code);
    }
}
