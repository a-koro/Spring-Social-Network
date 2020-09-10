package com.connector.beta.services;

import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostServiceInterface {

    public List<Post> findByUser(Integer userId);

    public List<Post> findByUserIds(List<Integer> userIds);

    public Post findPostByPostId(int postId);

    public void updatePost(Post post);
}
