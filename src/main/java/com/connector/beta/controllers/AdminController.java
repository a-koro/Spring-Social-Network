package com.connector.beta.controllers;

import com.connector.beta.services.CommentServiceInterface;
import com.connector.beta.services.PostServiceInterface;
import com.connector.beta.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    UserServiceInterface userServiceInterface;

    @Autowired
    PostServiceInterface postServiceInterface;

    @Autowired
    CommentServiceInterface commentServiceInterface;

    @GetMapping("/admin")
    public String adminSuccessPage(ModelMap mm) {
        mm.addAttribute("users", userServiceInterface.getAllUsers().size());
        mm.addAttribute("posts", postServiceInterface.getAllPosts());
        mm.addAttribute("comments", commentServiceInterface.getLengthOfPosts());
        return "admin";
    }
}
