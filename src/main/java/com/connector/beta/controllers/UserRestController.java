/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.controllers;

import com.connector.beta.dto.SearchImageDto;
import com.connector.beta.dto.UserDto;
import com.connector.beta.dto.UserNameWithImageDto;

import com.connector.beta.entities.Image;
import com.connector.beta.entities.MyUser;
import com.connector.beta.repos.UserRepo;
import com.connector.beta.services.UserServiceInterface;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author korov
 */
@Controller
public class UserRestController {

    @Autowired
    UserServiceInterface userServiceInterface;
    @Autowired
    UserRepo userRepo;

    @ResponseBody
    @PostMapping("/searchUser")
    public List<MyUser> searchUser(@RequestHeader String name) {
        return userServiceInterface.searchUsersByName(name);
    }

    @ResponseBody
    @GetMapping("/testGetMethod")
    public List<MyUser> testGetMethod() {
        return userServiceInterface.searchUsersByName("Alexandros");
    }

    @ResponseBody
    @GetMapping("/searchUsers")
    public ResponseEntity<List<SearchImageDto>> searchUsers(@RequestHeader String input) {
        List<UserNameWithImageDto> list = userServiceInterface.searchUserByFirstnameOrLastname(input);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentUserId = userRepo.findUserIdByEmail(user.getUsername()).orElseThrow(() -> new RuntimeException("Error: User Id not found"));

        List<SearchImageDto> search = list.stream()
                .filter(u -> u.getUserId() != currentUserId)
                .map(file -> new SearchImageDto(
                        file.getFirstName(),
                        file.getLastName(),
                        file.getUserId(),
                        file.getImage().getType(),
                        file.getImage().getSize()
                )).collect(Collectors.toList());

//        List<SearchImageDto> search = list.stream()
//                .filter(u -> u.getUserId() != currentUserId)
//                .map(file -> {
//                    String url = ServletUriComponentsBuilder
//                            .fromCurrentContextPath()
//                            .path("api/profile/searchUsers/")
//                            .path(file.getUserId().toString())
//                            .toUriString();
//
//                    return new SearchImageDto(
//                            file.getFirstName(),
//                            file.getLastName(),
//                            url,
//                            file.getImage().getType(),
//                            file.getImage().getSize()
//                    );
//                }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(search);
    }

    @ResponseBody
    @GetMapping("/userDetails")
    public UserDto getUserDetails(Principal principal) {
        return userServiceInterface.getCurrentUser();
    }

    // Test controller for testing entity infinite recursion
    @ResponseBody
    @GetMapping("/testSearch")
    public UserNameWithImageDto testSearch() {
        UserNameWithImageDto test = userServiceInterface.searchUserByFirstnameOrLastname("Ale").get(0);
        return test;
    }
}
