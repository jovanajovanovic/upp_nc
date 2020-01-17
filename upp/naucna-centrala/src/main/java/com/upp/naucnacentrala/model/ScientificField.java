package com.upp.naucnacentrala.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "scientific")
public class ScientificField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    public ScientificField(){}

    public ScientificField(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
