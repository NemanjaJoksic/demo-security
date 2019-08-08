/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author joksin
 */
@Getter
@Setter
@ToString
public class Authentication {
    
    private String principal;
    private String path;
    private String method;
    private List<String> roles;

    public Authentication(String principal, String path, String method, List<String> roles) {
        this.principal = principal;
        this.path = path;
        this.method = method;
        this.roles = roles;
    }
    
    public Authentication(String principal) {
        this(principal, null, null, null);
    }
    
    public Authentication(String principal, List<String> roles) {
        this(principal, null, null, roles);
    }
    
}
