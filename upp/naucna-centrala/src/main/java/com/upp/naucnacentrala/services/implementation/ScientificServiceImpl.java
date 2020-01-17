package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.services.ScientificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScientificServiceImpl implements ScientificService {

    @Autowired
    private ScientificRepository repo;

    @Override
    public List<ScientificField> getScientifics() {
        return (List<ScientificField>) repo.findAll();
    }
}
