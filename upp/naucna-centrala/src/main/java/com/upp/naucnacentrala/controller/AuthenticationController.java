package com.upp.naucnacentrala.controller;

import com.upp.naucnacentrala.dto.DTOLogin;
import com.upp.naucnacentrala.dto.DtoToken;
import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.UserRepository;
import com.upp.naucnacentrala.security.TokenUtils;

import com.upp.naucnacentrala.services.AuthenticationService;
import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.ws.rs.BadRequestException;


@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IdentityService identityService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody DTOLogin dtoLogin){
        System.out.println(">> login : username " + dtoLogin.getUsername() + " pass " + dtoLogin.getPassword());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            throw  new BadRequestException("Unauthorized!");
        }

        try{
            if(this.authenticationService.login(dtoLogin)){
                HttpHeaders httpHeaders = new HttpHeaders();
                DtoToken token = new DtoToken();
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(dtoLogin.getUsername());
                String tokenValue = this.tokenUtils.generateToken(userDetails);
                token.setToken(tokenValue);
                User u = this.userRepository.findByUsername(dtoLogin.getUsername());
                token.setRole(u.getRole());

                Authentication auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(auth);
                httpHeaders.add("X-Auth-Token", tokenValue);
                identityService.setAuthenticatedUserId(u.getUsername());
                return new ResponseEntity<DtoToken>(token, httpHeaders, HttpStatus.OK);

            }
            return null;

        }catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("Error occured!");
        }
    }

}
