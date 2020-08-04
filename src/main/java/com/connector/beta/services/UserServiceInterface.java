/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.services;

import com.connector.beta.entities.MyUser;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author korov
 */
public interface UserServiceInterface extends UserDetailsService {
    
    public List<MyUser> searchUsersByName(String name);
}
