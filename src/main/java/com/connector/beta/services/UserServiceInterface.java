/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.services;

import com.connector.beta.dto.UserDto;

import com.connector.beta.dto.UserProfileImageDto;
import com.connector.beta.entities.MyUser;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author korov
 */
public interface UserServiceInterface extends UserDetailsService {
    
     List<MyUser> searchUsersByName(String name);

     List<Object[]> searchUserByFirstnameOrLastname(String input);

     UserDto getCurrentUser();

     List<UserDto> getAllUsers();

     MyUser findById(Integer userid);

     public Integer findUserIdByEmail(String email);

    String findCurrentUsername();

    void userSave(MyUser user);


}
