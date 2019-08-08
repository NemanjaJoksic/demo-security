/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author joksin
 */
@Entity
@Table(name = "ROLE")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    public static final String USER_ROLE = "USER_ROLE";
    public static final String ADMIN_ROLE = "ADMIN_ROLE";
    public static final String GUEST_ROLE = "GUEST_ROLE";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Integer id;

    @Column(name = "name")
    @JsonProperty(value = "name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
    
    public static List<String> getRoleNames(List<Role> roles) {
        if(roles == null)
            return Collections.EMPTY_LIST;
        
        List<String> roleNames = new LinkedList<>();
        roles.forEach(role -> roleNames.add(role.getName()));
        return roleNames;
    }

}

