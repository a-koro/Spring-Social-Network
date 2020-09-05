/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.controllers;

import com.connector.beta.entities.MyUser;
import com.connector.beta.services.UserServiceInterface;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author korov
 */
@Controller
public class UserRestController {

    @Autowired
    UserServiceInterface userServiceInterface;

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
    public List<Object[]> searchUsers(@RequestHeader String input) {
        return userServiceInterface.searchUserByFirstnameOrLastname(input);
    }

    @ResponseBody
    @GetMapping("/userDetails")
    public MyUser getUserDetails(Principal principal) {
        return userServiceInterface.getUserDetails(principal.getName());
    }
}
