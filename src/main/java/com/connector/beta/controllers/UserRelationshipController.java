package com.connector.beta.controllers;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.Pojos.UserRelationshipParams;
import com.connector.beta.dto.NewsFeedDTO;
import com.connector.beta.entities.UserRelationship;
import com.connector.beta.entities.UserRelationshipKey;
import com.connector.beta.repos.UserRelationshipRepo;
import com.connector.beta.repos.UserRepo;
import com.connector.beta.services.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentUserId = userRepo.findUserIdByEmail(user.getUsername()).orElseThrow(() -> new RuntimeException("Error: User Id not found"));

        List<UserFriendsDto> friendsDto = userRelationshipRepo.getAllFriendsWithNames(currentUserId);
        List<UserFriendsDto> friendsDtoSecond = userRelationshipRepo.getAllFriendsWithNamesSecond(currentUserId);

        List<UserFriendsDto> friendsDtoFiltered = Stream.concat(friendsDto.stream(), friendsDtoSecond.stream())
                .filter(friend -> !friend.getEmail().equals(user.getUsername()))
                .sorted(Comparator.comparingInt(UserFriendsDto::getUserFirstId))
                .collect(Collectors.toList());

        friendsDtoFiltered.forEach(f -> {
            if (f.getUserFirstId() != currentUserId) {
                f.setUserSecondId(f.getUserFirstId());
                f.setUserFirstId(currentUserId);
            }
        });
//         friendsDtoFiltered.forEach( f-> System.out.println(f.getUserFirstId() + " " + f.getUserSecondId()));


//        Getting all Friends' Ids of the Current Logged-in User and adding them to List: friendsIds
        List<Integer> friendsIds = new ArrayList<>();
        friendsDtoFiltered.forEach(friend -> {
            if (friend.getUserFirstId() != currentUserId) {
                friendsIds.add(friend.getUserFirstId());
            } else {
                friendsIds.add(friend.getUserSecondId());
            }
        });
        friendsIds.forEach(System.out::println);
//        Done

        return ResponseEntity.status(HttpStatus.OK)
                .body(friendsDtoFiltered);
    }

    @Transactional
    @PostMapping("/deleteRelationship")
    public ResponseEntity deleteUsersRelationship(@Valid @RequestBody UserRelationshipParams userParams) {

        if (userParams.getCurrentUserId() > userParams.getProfilePageId()) {
            int temp = userParams.getCurrentUserId();
            userParams.setCurrentUserId(userParams.getProfilePageId());
            userParams.setProfilePageId(temp);
        }
        userRelationshipRepo.deleteRelationship(userParams.getCurrentUserId(), userParams.getProfilePageId());
        return null;
    }

    @PostMapping("/relationship")
//    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserRelationship> getUsersRelationship(@Valid @RequestBody UserRelationshipParams userParams) {
        System.out.println("current User Id + " + userParams.getCurrentUserId() + " profile Page Id + " + userParams.getProfilePageId());

//        UserRelationship myFirstRelationship = null;

        UserRelationship myFirstRelationship = userRelationshipRepo.CheckRelationshipIfExists(userParams.getProfilePageId(), userParams.getCurrentUserId());
        if (myFirstRelationship == null) {
            UserRelationship userRelationship = new UserRelationship();
            UserRelationshipKey userRelationshipKey = new UserRelationshipKey();
            userRelationshipKey.setUserFirstId(-1);
            userRelationshipKey.setUserSecondId(-1);
            userRelationship.setId(userRelationshipKey);
            System.out.println("No friend relationship between Ids");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userRelationship);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(myFirstRelationship);
    }


    // This controller is an updated version of the above to send the posts and the connections at the same time
    @GetMapping("/newsFeed")
    public ResponseEntity<NewsFeedDTO> getFriendsAndPosts() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentUserId = userRepo.findUserIdByEmail(user.getUsername()).orElseThrow(() -> new RuntimeException("Error: User Id not found"));

        List<UserFriendsDto> friendsDto = userRelationshipRepo.getAllFriendsWithNames(currentUserId);
        List<UserFriendsDto> friendsDtoSecond = userRelationshipRepo.getAllFriendsWithNamesSecond(currentUserId);

        List<UserFriendsDto> friendsDtoFiltered = Stream.concat(friendsDto.stream(), friendsDtoSecond.stream())
                .filter(friend -> !friend.getEmail().equals(user.getUsername()))
                .sorted(Comparator.comparingInt(UserFriendsDto::getUserFirstId))
                .collect(Collectors.toList());


//        All friends placed at userSecondId

        friendsDtoFiltered.forEach(f -> {
            if (f.getUserFirstId() != currentUserId) {
                f.setUserSecondId(f.getUserFirstId());
                f.setUserFirstId(currentUserId);
            }
        });

        List<Integer> friendsIds = new ArrayList<>();
        friendsDtoFiltered.forEach(f -> {
            friendsIds.add(f.getUserSecondId());
        });

        NewsFeedDTO newsFeedDto = new NewsFeedDTO();
        newsFeedDto.setPosts(postServiceInterface.findByUserIds(friendsIds));
        newsFeedDto.setFriends(friendsDtoFiltered);

        return ResponseEntity.status(HttpStatus.OK)
                .body(newsFeedDto);
    }

}
