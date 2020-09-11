package com.connector.beta.controllers;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.dto.NewsFeedDTO;
import com.connector.beta.repos.UserRelationshipRepo;
import com.connector.beta.repos.UserRepo;
import com.connector.beta.services.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRelationshipController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserRelationshipRepo userRelationshipRepo;
    @Autowired
    PostServiceInterface postServiceInterface;



    @GetMapping("/user")
    public ResponseEntity<List<UserFriendsDto>> CurrentUserInfo() {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userFirstId = userRepo.findUserIdByEmail(user.getUsername()).orElseThrow( () -> new RuntimeException("Error: User Id not found"));
        List<UserFriendsDto> friendsDto = userRelationshipRepo.getAllFriendsWithNames(userFirstId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(friendsDto);
    }

    // This controller is an updated version of the above to send the posts and the connections at the same time
    @GetMapping("/newsFeed")
    public ResponseEntity<NewsFeedDTO> getFriendsAndPosts() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userFirstId = userRepo.findUserIdByEmail(user.getUsername()).orElseThrow( () -> new RuntimeException("Error: User Id not found"));

        NewsFeedDTO newsFeedDto = new NewsFeedDTO();
        List<UserFriendsDto> friendsDto = userRelationshipRepo.getAllFriendsWithNames(userFirstId);
        List<Integer> userIds = new ArrayList<>();
        friendsDto.forEach(friend -> {
            userIds.add(friend.getUserSecondId());
        });
        newsFeedDto.setPosts(postServiceInterface.findByUserIds(userIds));

        newsFeedDto.setFriends(friendsDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(newsFeedDto);
    }

}
