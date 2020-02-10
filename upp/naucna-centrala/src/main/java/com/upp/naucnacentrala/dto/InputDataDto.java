package com.upp.naucnacentrala.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InputDataDto implements Serializable {
   private String name;
   private Object value;
   private boolean isList;

    public InputDataDto() {
    }

    public InputDataDto(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public InputDataDto(String name, Object value, boolean isList) {
        this.name = name;
        this.value = value;
        this.isList = isList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    @Override
    public String toString() {
        return "InputDataDto{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
