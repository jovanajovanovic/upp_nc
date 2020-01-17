package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.dto.DTOLogin;
import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.UserRepository;
import com.upp.naucnacentrala.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(DTOLogin dto) {
        return userRepository.findByUsername(dto.getUsername()) != null;
    }


}
