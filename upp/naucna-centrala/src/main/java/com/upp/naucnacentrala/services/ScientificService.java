package com.upp.naucnacentrala.services;


import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.ScientificField;

import java.util.List;

public interface ScientificService {
    List<ScientificField> getScientifics();
    List<String> getScientificFields(Object keyValue) throws ObjectNotFound;

    String getScientificField(Object value) throws ObjectNotFound;
}
