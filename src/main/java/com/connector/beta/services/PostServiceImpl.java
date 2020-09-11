package com.connector.beta.services;

import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;
import com.connector.beta.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostServiceInterface {

    @Autowired
    PostRepo postRepo;

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
}
