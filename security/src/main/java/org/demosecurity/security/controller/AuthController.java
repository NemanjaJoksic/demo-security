/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.controller;

import javax.servlet.http.HttpServletRequest;
import org.demosecurity.security.model.User;
import org.demosecurity.security.service.AuthenticationService;
import org.demosecurity.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joksin
 */
@RestController
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping("/login")
    public ResponseEntity getAuthToken(HttpServletRequest request) throws Exception {
        return new ResponseEntity(authenticationService.obtainAccessToken(request), HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody User user, HttpServletRequest request) throws Exception {
        User createdUser = userService.createUser(user, request.getRemoteHost());
        createdUser.setPassword(null);
        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }
    
}
