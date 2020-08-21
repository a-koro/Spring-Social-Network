/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.controllers;

import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Role;
import com.connector.beta.repos.RoleRepo;
import com.connector.beta.repos.UserRepo;
import com.connector.beta.services.UserServiceImpl;
import com.connector.beta.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author korov
 */

@Controller
public class AuthenticationController {

//    @Autowired
//    UserServiceInterface userServiceInterface;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;


    @GetMapping("/register")
    public String registerPage(ModelMap mm) {
        mm.addAttribute("newUser", new MyUser());
        System.out.println(MyUser.class);
        return "register";
    }

    @PostMapping("/doregister")
    public String registrationSubmit(ModelMap mm,
                                  @ModelAttribute("newUser") MyUser myUser) {

//        System.out.println(myUser.getBirthday());
//        System.out.println(myUser.getPassword() + "lol");
//        System.out.println(passwordEncoder.encode(myUser.getPassword()));

        String encodedPass = passwordEncoder.encode(myUser.getPassword());
        myUser.setPassword(encodedPass);

        List<Role> roles = new ArrayList<>();
//        Hard Coding User role for every new Registration
        Role userRole = roleRepo.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
        roles.add(userRole);

        //        System.out.println(userRole.getClass());
        myUser.setRoles(roles);
        userRepo.save(myUser);
        return null;
    }
}
