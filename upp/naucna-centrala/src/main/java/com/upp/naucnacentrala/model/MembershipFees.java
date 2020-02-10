package com.upp.naucnacentrala.model;

import javax.persistence.*;

@Entity
@Table(name = "fees")
public class MembershipFees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Magazine magazine;

    @ManyToOne
    @JoinColumn
    private User user;

    public MembershipFees() {
    }

    public MembershipFees(Magazine magazine, User user) {
        this.magazine = magazine;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
