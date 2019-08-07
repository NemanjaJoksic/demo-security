/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.app.controller;

import org.demosecurity.commons.model.ApiResponse;
import org.demosecurity.security.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joksin
 */
@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public ResponseEntity test() throws Exception {
        String message = "Hello " + SecurityContext.current().getAuthentication().getPrincipal() + "!";
        return new ResponseEntity(ApiResponse.success(message), HttpStatus.OK);
    }
    
}
