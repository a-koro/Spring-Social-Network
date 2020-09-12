package com.connector.beta.services;

import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;
import com.connector.beta.entities.PostImage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostServiceInterface {

    public List<Post> findByUser(Integer userId);

    public List<Post> findByUserIds(List<Integer> userIds);

    public Post findPostByPostId(int postId);

    public void updatePost(Post post);

    public void insertPost(Post post);

    public void insertPostWithImage(String text, MultipartFile file, MyUser user);

    public PostImage findPostImageByPostId(int postId);
}
