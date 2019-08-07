/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.model;

import lombok.Getter;

/**
 *
 * @author joksin
 */
@Getter
public class Authentication {
    
    private final String principal;
    
    public Authentication(String principal) {
        this.principal = principal;
    }
    
}
