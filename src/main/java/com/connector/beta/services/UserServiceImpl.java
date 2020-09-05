/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.services;

import com.connector.beta.dto.UserDto;
import com.connector.beta.entities.Image;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Role;
import com.connector.beta.mapper.UserMapper;
import com.connector.beta.repos.UserRepo;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser myUser = userRepo.findByEmail(email)
                .orElseThrow(
                () ->
                        new UsernameNotFoundException("Email not found - " + email));
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
    public UserDto getCurrentUser() {
        UserDto userDto=new UserDto();
        try {
            MyUser myUser = userRepo.findByEmail( findCurrentUsername())
                    .orElseThrow(
                            () ->
                                    new UsernameNotFoundException("Email not found - " + findCurrentUsername()));

            userDto = userMapper.mapToDto(myUser);

            return userDto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userMapper.mapListToDto(userRepo.findAll());
    }

    @Override
    public MyUser findById(Integer userid) {
       return userRepo.findById(userid)
               .orElseThrow(
                       ()->
                       new IllegalArgumentException("id not found") );

    }

    @Override
    public Integer findUserIdByEmail(String email){
        return userRepo.findUserIdByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("id not found"));
    }

    @Override
    public String findCurrentUsername(){
            User user = (User) SecurityContextHolder.
                    getContext().getAuthentication().getPrincipal();
            return user.getUsername();

    }

    @Override
    public void userSave(MyUser user){
        userRepo.save(user);
    }

    @Override
    public Image findImageProfileFromUserId(Integer userid){
        return userRepo.findImageProfileFromUserId(userid).orElseThrow(()->new IllegalArgumentException("not found"));
    }


    @Override
    public MyUser getUserDetails(String email) {
        return userRepo.findByEmail(email);
    }


}
