/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.services;

import com.connector.beta.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author korov
 */

@Service
public class RoleServiceImpl implements RoleServiceInterface {
    
    @Autowired
    RoleRepo roleRepo;
}
