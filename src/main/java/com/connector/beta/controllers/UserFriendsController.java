package com.connector.beta.controllers;

import com.connector.beta.entities.FriendsEntity;
import com.connector.beta.entities.FriendsEntityPK;
import com.connector.beta.entities.MyUser;
import com.connector.beta.repos.FriendsRepo;
import com.connector.beta.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserFriendsController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    FriendsRepo friendsRepo;


    @GetMapping("/user")
    public ResponseEntity<MyUser> CurrentUserInfo() {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        System.out.println(authentication.getName());
//        System.out.println(authentication.getPrincipal().getClass());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(user.getUsername());

        MyUser myUser = userRepo.findByEmail(user.getUsername());



//        System.out.println(myUser);
//        System.out.println(myUser.getBirthday());
        FriendsEntityPK friendsEntityPK = new FriendsEntityPK();


        List<FriendsEntity> friends = friendsRepo.findAll();
        System.out.println(friends.get(0).getUserFirstId());
        System.out.println(friends.get(1).getUserFirstId());
        System.out.println(friends.get(2).getUserFirstId());
        System.out.println(friends.get(3).getUserFirstId());

        System.out.println(friends.get(0).getUserSecondId());
        System.out.println(friends.get(1).getUserSecondId());
        System.out.println(friends.get(2).getUserSecondId());
        System.out.println(friends.get(3).getUserSecondId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(myUser);
    }


}
