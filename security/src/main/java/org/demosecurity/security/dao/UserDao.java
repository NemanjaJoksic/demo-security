/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.dao;

import org.demosecurity.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joksin
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {
    
    public User findByUsername(String username);
    
}
