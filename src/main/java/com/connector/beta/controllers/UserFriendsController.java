package com.connector.beta.controllers;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.UserFriends;
import com.connector.beta.repos.UserFriendsRepo;
import com.connector.beta.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    UserFriendsRepo userFriendsRepo;



    @GetMapping("/user")
    public ResponseEntity<List<UserFriendsDto>> CurrentUserInfo() {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        System.out.println(authentication.getName());
//        System.out.println(authentication.getPrincipal().getClass());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(user.getUsername());

//        MyUser myUser = userRepo.findByEmailNotOptional(user.getUsername());

        int userSecondId = 1;


        List<UserFriendsDto> friendsDto = userFriendsRepo.getAllFriendsWithNames(userSecondId);
//        System.out.println(friendsDto.get(0).getFirstName());
//        System.out.println(friendsDto.get(0).getLastName());

//        List<UserFriends> userFriendsListTrue = userFriendsRepo.getAllFriends(userSecondId);
//        System.out.println(userFriendsListTrue.get(0).getId().getUserFirstId());
//        System.out.println(userFriendsListTrue.get(0).getId().getUserSecondId());
//        System.out.println(userFriendsListTrue.get(0).getStatus());
//        userFriendsListTrue.get(0).getMyUser1().
//
//        System.out.println(userFriendsListTrue.get(1).getId().getUserFirstId());
//        System.out.println(userFriendsListTrue.get(1).getId().getUserSecondId());
//        System.out.println(userFriendsListTrue.get(1).getStatus());
//
//        System.out.println(userFriendsListTrue.get(2).getId().getUserFirstId());
//        System.out.println(userFriendsListTrue.get(2).getId().getUserSecondId());
//        System.out.println(userFriendsListTrue.get(2).getStatus());

//        List<Object[]> userFriendsList1 = userFriendsRepo.getAllFriendsWithNames(userSecondId);
//        System.out.println(userFriendsList1.get(0).toString());


//        List<UserFriends> userFriendsList = userFriendsRepo.findAll();
//        System.out.println(userFriendsList.get(0).toString());
//        System.out.println(userFriendsList.get(1).toString());

        return ResponseEntity.status(HttpStatus.OK)
                .body(friendsDto);
    }


}
