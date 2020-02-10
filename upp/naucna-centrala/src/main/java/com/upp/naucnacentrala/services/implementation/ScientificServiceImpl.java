package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.exceptions.ObjectNotFound;
import com.upp.naucnacentrala.model.ScientificField;
import com.upp.naucnacentrala.repository.ScientificRepository;
import com.upp.naucnacentrala.services.ScientificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ScientificServiceImpl implements ScientificService {

    @Autowired
    private ScientificRepository repo;

    @Override
    public List<ScientificField> getScientifics() {
        return (List<ScientificField>) repo.findAll();
    }

    @Override
    public List<String> getScientificFields(Object keyValue) throws ObjectNotFound {
        System.out.println(keyValue.getClass());
        ArrayList<String> keyValues = new ArrayList<>();
        if(keyValue instanceof String) {
           String keys = (String) keyValue;
           keys = keys.replace("[", "");
           keys = keys.replace("]", "");
           String[] tokens = keys.split(",");
           for(String s : tokens){
               keyValues.add(s.trim());
           }
        }else {
            keyValues = (ArrayList<String>) keyValue;
        }

        List<String> scientific = new ArrayList<>();
        for (String kv : keyValues){
            System.out.println(kv.trim());
            ScientificField sf = repo.findByCode(kv.trim()).orElseThrow(()-> new ObjectNotFound("Scientific field does not exist"));
            scientific.add(sf.getName());
        }
        return scientific;
    }

    @Override
    public String getScientificField(Object value) throws ObjectNotFound {
        String code = (String)value;
        ScientificField sf = repo.findByCode(code).orElseThrow(()-> new ObjectNotFound("Scientific field with code " + code +" _does not exist!"));

        return sf.getName();
    }
}
