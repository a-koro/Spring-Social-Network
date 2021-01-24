package com.connector.beta.controllers;

import com.connector.beta.dto.UserDto;
import com.connector.beta.projections.MyUserProjection;
import com.connector.beta.projections.PostProjection;
import com.connector.beta.entities.*;
import com.connector.beta.repos.CommentViewedRepo;
import com.connector.beta.repos.PostRepo;
import com.connector.beta.services.CommentServiceInterface;
import com.connector.beta.services.PostServiceInterface;
import com.connector.beta.services.UserServiceInterface;
import com.connector.beta.websocketRepo.ChatMessageRepo;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServiceInterface postServiceInterface;

    @Autowired
    CommentServiceInterface commentServiceInterface;

    @Autowired
    UserServiceInterface userServiceInterface;

    @Autowired
    CommentViewedRepo commentViewedRepo;

    @Autowired
    ChatMessageRepo chatMessageRepo;

    @GetMapping("/testUrl")
    public List<PostProjection> testRetrievePots(Principal principal) {
//        System.out.println(principal.toString());
//        return postServiceInterface.findByUser(1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        return postServiceInterface.findByUserIdsTEST();
    }

    @PostMapping("/insertComment")
    public Comment insertComment(@RequestHeader String input, @RequestHeader int postId, Principal principal) {
        Comment comment = new Comment();
        comment.setText(input);
        comment.setPost(postServiceInterface.findPostByPostId(postId));
        comment.setUser(userServiceInterface.getUserDetails(principal.getName()));
        comment.setCreated(new Timestamp(System.currentTimeMillis()));
//        comment.setViewed(false);
        commentServiceInterface.insertComment(comment);
        CommentViewed cv = new CommentViewed();
        cv.setComment(comment);
        cv.setViewed(false);
        commentViewedRepo.save(cv);
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

    @PostMapping(
            path = "/insertPostWithFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public void insertPostWithFile(
            @RequestParam("file") MultipartFile file,
            @RequestHeader String text,
            Principal principal) {
        postServiceInterface.insertPostWithImage(text, file, userServiceInterface.getUserDetails(principal.getName()));
    }

    @GetMapping("/downloadPostImage/{postId}")
    public ResponseEntity<Resource> downloadPostImage(@PathVariable Integer postId) {
        PostImage postImage = postServiceInterface.findPostImageByPostId(postId);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=" + postImage.getTitle());
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(postImage.getFile().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(postImage.getFile()));
    }

    @PostMapping("/cheers")
    public List<Cheer> cheers(@RequestHeader int postId, Principal principal) {
        Post post = postServiceInterface.findPostByPostId(postId);
        MyUser user = userServiceInterface.getUserDetails(principal.getName());
        return postServiceInterface.cheers(post, user);
    }

    @PostMapping("/removePost")
    @ResponseStatus(HttpStatus.OK)
    public String removePost(@RequestHeader int postId, Principal principal) {
        Post post = postServiceInterface.findPostByPostId(postId);
        MyUser user = userServiceInterface.getUserDetails(principal.getName());
        if (post.getUser().getUserId() == user.getUserId()) {
            postServiceInterface.removePost(post);
        }
        return "Post Deleted";
    }

    @PostMapping("/removeComment")
    @ResponseStatus(HttpStatus.OK)
    public String removeComment(@RequestHeader int commentId, Principal principal) {
        Comment comment = commentServiceInterface.findCommentByCommentId(commentId);
        MyUser user = userServiceInterface.getUserDetails(principal.getName());
        if (comment.getUser().getUserId() == user.getUserId()) {
            commentServiceInterface.removeComment(comment);
        }
        return "Comment Deleted";
    }

    // New endpoint to update posts
    @PostMapping(path = "/updatePost", produces = "application/json")
    public ResponseEntity updatePost(@RequestHeader int postId,@RequestBody Map<String, String> body) {
        try {
            Post postToUpdate = postServiceInterface.findPostByPostId(postId);
            UserDto currentUser = userServiceInterface.getCurrentUser();
            if ((postToUpdate.getUser().getUserId() == currentUser.getUserId()) && (body.get("text").length() <= 250)) {
                postToUpdate.setText(body.get("text").trim());
                postServiceInterface.updatePost(postToUpdate);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(403).build();
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).build();
        }
    }

    // New endpoint to update comments
    @PostMapping("/updateComment")
    public ResponseEntity updateComment(@RequestHeader int commentId, @RequestBody Map<String, String> body) {
        try {
            Comment commentToUpdate = commentServiceInterface.findCommentByCommentId(commentId);
            UserDto currentUser = userServiceInterface.getCurrentUser();
            if((commentToUpdate.getUser().getUserId() == currentUser.getUserId()) && (body.get("text").length() <= 250)) {
                commentToUpdate.setText(body.get("text").trim());
                commentServiceInterface.updateComment(commentToUpdate);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(403).build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getNewComments")
    public ResponseEntity getNewComments() {

        MyUser user = userServiceInterface.getUserDetails(userServiceInterface.findCurrentUsername());
        List<Comment> newComments = commentServiceInterface.getNewComments(user);

        return ResponseEntity.status(HttpStatus.OK).body(newComments);
    }

    @GetMapping("/getSpecificPost/{commentId}")
    public ResponseEntity getSpecificPost(@PathVariable Integer commentId) {

        try {
            Post post = postServiceInterface.getSpecificPostFromCommentId(commentId);
            commentServiceInterface.markCommentAsViewed(commentId);
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(500).build();
        }

    }

    @GetMapping("/getUsersPosts/{userId}")
    public ResponseEntity getUsersPosts(@PathVariable Integer userId, HttpServletRequest request) {
        try {
            List<Integer> friendsIds = (List<Integer>)request.getSession().getAttribute("friendsIds");
            if(friendsIds.contains(userId)) {
                return ResponseEntity.status(HttpStatus.OK).body(postServiceInterface.findByUser(userId));
            }
            return ResponseEntity.status(403).build();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(500).build();
        }

    }

    @GetMapping("/testNewMessengerConnectionsListMethod")
    public List<MyUserProjection> getMessengerConnectionsList(HttpServletRequest request) {

        Integer loggedInUserId = (Integer)request.getSession().getAttribute("loggedInUserId");
        List<MyUserProjection> listOfChats = chatMessageRepo.getAllChatsOrderedByLastMessage(loggedInUserId);
        return listOfChats;
    }
}
