package com.upp.naucnacentrala.dto;

import com.upp.naucnacentrala.model.MagazineType;

import java.io.Serializable;
import java.util.List;

public class MagazineRegisterDto implements Serializable {

    private String name;
    private String issn;
    private String payment;
    private List<String> scientific;
    private String chiefEditor;

    public MagazineRegisterDto(String name, String issn, String payment, List<String> scientific, String chiefEditor) {
        this.name = name;
        this.issn = issn;
        this.payment = payment;
        this.scientific = scientific;
        this.chiefEditor = chiefEditor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<String> getScientific() {
        return scientific;
    }

    public void setScientific(List<String> scientific) {
        this.scientific = scientific;
    }

    public String getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(String chiefEditor) {
        this.chiefEditor = chiefEditor;
    }

    @Override
    public String toString() {
        return "MagazineRegisterDto{" +
                "name='" + name + '\'' +
                ", issn='" + issn + '\'' +
                ", payment='" + payment + '\'' +
                ", scientific=" + scientific +
                ", chiefEditor='" + chiefEditor + '\'' +
                '}';
    }
}
