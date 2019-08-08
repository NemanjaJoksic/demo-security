/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.dao;

import org.demosecurity.security.model.Role;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joksin
 */
@Repository
@ConditionalOnProperty(name = "security.user.store.type", havingValue = "rdb")
public interface RoleDao extends JpaRepository<Role, String> {
    
}
