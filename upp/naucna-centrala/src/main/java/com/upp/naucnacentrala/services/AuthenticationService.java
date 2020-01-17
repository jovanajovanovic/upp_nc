package com.upp.naucnacentrala.services;

import com.upp.naucnacentrala.dto.DTOLogin;
import com.upp.naucnacentrala.model.User;

public interface AuthenticationService {

    boolean login(DTOLogin dto);
}
