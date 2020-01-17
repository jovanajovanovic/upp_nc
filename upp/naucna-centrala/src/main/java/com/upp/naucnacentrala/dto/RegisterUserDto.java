package com.upp.naucnacentrala.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RegisterUserDto implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String city;
    private String country;
    private String metier;
    private String username;
    private boolean reviewer;
    private List<String> scientific;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isReviewer() {
        return reviewer;
    }

    public void setReviewer(boolean reviewer) {
        this.reviewer = reviewer;
    }

    public List<String> getScientific() {
        return scientific;
    }

    public void setScientific(List<String> scientific) {
        this.scientific = scientific;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", metier='" + metier + '\'' +
                ", username='" + username + '\'' +
                ", reviewer=" + reviewer +
                ", scientificFields=" + scientific +
                '}';
    }
}
