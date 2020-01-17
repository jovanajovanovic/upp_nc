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
}
