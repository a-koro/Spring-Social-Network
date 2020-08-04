/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.repos;

import com.connector.beta.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author korov
 */

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    
}
