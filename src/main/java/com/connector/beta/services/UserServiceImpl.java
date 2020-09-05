/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.services;

import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Role;
import com.connector.beta.repos.UserRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author korov
 */

@Transactional
@Service
public class UserServiceImpl implements UserServiceInterface {
    
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser myUser = userRepo.findByEmail(email);
        if (myUser == null) {
            throw new UsernameNotFoundException("Invalid username");
        }
        User springSecurityUser = 
                new User(myUser.getEmail(), 
                myUser.getPassword(), 
                mapRolesToAuthorities(myUser.getRoles()));
        return springSecurityUser;
    }
    
    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public List<MyUser> searchUsersByName(String name) {
        return userRepo.findByFirstName(name);
    }

    @Override
    public List<MyUser> searchUserByFirstnameOrLastname(String input) {
        return userRepo.getUsersByFirstnameAndLastname(input);
    }

    @Override
    public MyUser getUserDetails(String email) {
        return userRepo.findByEmail(email);
    }


}
