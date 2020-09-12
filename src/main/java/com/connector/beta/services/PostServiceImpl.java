package com.connector.beta.services;

import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;
import com.connector.beta.entities.PostImage;
import com.connector.beta.repos.PostImageRepo;
import com.connector.beta.repos.PostRepo;
import com.connector.beta.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostServiceImpl implements PostServiceInterface {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostImageRepo postImageRepo;

    @Autowired
    ImageServiceInterface imageServiceInterface;

    @Override
    public List<Post> findByUser(Integer userId) {
        return postRepo.findByUserUserIdOrderByCreatedDesc(userId);
    }

    @Override
    public List<Post> findByUserIds(List<Integer> userIds) {
        List<Post> list = postRepo.findByUserUserIdInOrderByCreatedDesc(userIds);
//        list.forEach(post -> {
//            MyUser user = post.getUser();
//            user.setPassword("");
//            post.setUser(user);
//
//            post.getComments().forEach(comment -> {
//                MyUser user1 = comment.getUser();
//                user1.setPassword("");
//                post.setUser(user1);
//            });
//
//            post.getCheers().forEach(cheer -> {
//                MyUser user2 = cheer.getUser();
//                user2.setPassword("");
//                cheer.setUser(user2);
//            });
//        });
        return list;
    }

    @Override
    public Post findPostByPostId(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("No data!"));
        return post;
    }

    @Transactional
    @Override
    public void updatePost(Post post) {
        postRepo.save(post);
    }

    @Transactional
    @Override
    public void insertPost(Post post) {
        postRepo.save(post);
    }

    @Transactional
    @Override
    public void insertPostWithImage(String text, MultipartFile file, MyUser user) {

        imageServiceInterface.isEmpty(file);
        imageServiceInterface.isImage(file);

        PostImage postImage = new PostImage();
        postImage.setTitle(file.getOriginalFilename());
        postImage.setType(file.getContentType());
        postImage.setSize(String.valueOf(file.getSize()));
        try{
            postImage.setFile(file.getBytes());
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Post post = new Post();
        post.setText(text);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUser(user);
        postImage.setPost(post);

        postImageRepo.save(postImage);
    }

    @Override
    public PostImage findPostImageByPostId(int postId) {
        return postImageRepo.findByPostPostId(postId);
    }
}
