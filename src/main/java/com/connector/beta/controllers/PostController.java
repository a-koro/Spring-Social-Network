package com.connector.beta.controllers;

import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;
import com.connector.beta.services.CommentServiceInterface;
import com.connector.beta.services.PostServiceInterface;
import com.connector.beta.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServiceInterface postServiceInterface;

    @Autowired
    CommentServiceInterface commentServiceInterface;

    @Autowired
    UserServiceInterface userServiceInterface;

    @GetMapping("/testUrl")
    public List<Post> testRetrievePots(Principal principal) {
//        System.out.println(principal.toString());
//        return postServiceInterface.findByUser(1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return postServiceInterface.findByUserIds(list);
    }

    @PostMapping("/insertComment")
    public Comment insertComment(@RequestHeader String input, @RequestHeader int postId, Principal principal) {
        Comment comment = new Comment();
        comment.setText(input);
        comment.setPost(postServiceInterface.findPostByPostId(postId));
        comment.setUser(userServiceInterface.getUserDetails(principal.getName()));
        comment.setCreated(new Timestamp(System.currentTimeMillis()));
        commentServiceInterface.insertComment(comment);
        return comment;
    }

    @PostMapping("/insertPost")
    public Post insertPost(@RequestHeader String text, @RequestHeader String imageUrl, Principal principal) {
        Post post = new Post();
        post.setUser(userServiceInterface.getUserDetails(principal.getName()));
        post.setText(text);
        post.setImageUrl(imageUrl);
        System.out.println(imageUrl);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        postServiceInterface.insertPost(post);
        return new Post();
    }

    @PostMapping("/insertPostWithFile")
    public Post insertPostWithFile() {
        return new Post();
    }
}
