package com.upp.naucnacentrala.controller;

import com.upp.naucnacentrala.security.TokenUtils;
import jdk.nashorn.internal.parser.Token;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ArticleController {

    @Autowired
    IdentityService identityService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    RuntimeService runtimeService;

    @GetMapping(path="/runAddArticle", produces = "application/json")
    public @ResponseBody
    boolean runAddMagazine(HttpServletRequest http){
        HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("X-Auth-Token");

        String username = this.tokenUtils.getUsernameFromToken(authToken);

        identityService.setAuthenticatedUserId(username);

        ProcessInstance pi = runtimeService.startProcessInstanceByKey("objava_rada");

        runtimeService.setVariable(pi.getProcessInstanceId(), "starter", username);
        System.out.println("RUN ADD ARTICLE PROCES");


        return true;
    }
}
