/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.services;

import com.connector.beta.dto.UserDto;
import com.connector.beta.dto.UserNameWithImageDto;
import com.connector.beta.entities.Image;
import com.connector.beta.entities.ImageBackground;
import com.connector.beta.entities.MyUser;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author korov
 */
public interface UserServiceInterface extends UserDetailsService {
    
     List<MyUser> searchUsersByName(String name);
//
//     List<Object[]> searchUserByFirstnameOrLastname(String input);

     UserDto getCurrentUser();

     UserDto getCurrentUser(int id);

     List<UserDto> getAllUsers();

     MyUser findById(Integer userid);

      Integer findUserIdByEmail(String email);

    String findCurrentUsername();

    void userSave(MyUser user);

    Image findImageProfileFromUserId(Integer userid);

     List<UserNameWithImageDto> searchUserByFirstnameOrLastname(String input);

     MyUser getUserDetails(String email);

     ImageBackground findImageBackgroundFromUserId(Integer userid);
}
