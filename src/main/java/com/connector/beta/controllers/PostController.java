package com.connector.beta.controllers;

import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;
import com.connector.beta.services.PostServiceInterface;
import com.connector.beta.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServiceInterface postServiceInterface;

    @GetMapping("/testUrl")
    public List<Post> testRetrievePots(Principal principal) {
//        System.out.println(principal.toString());
//        return postServiceInterface.findByUser(1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return postServiceInterface.findByUserIds(list);
    }
}
