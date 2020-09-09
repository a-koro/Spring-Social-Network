package com.connector.beta.services;

import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;

import java.util.List;

public interface PostServiceInterface {

    public List<Post> findByUser(Integer userId);

    public List<Post> findByUserIds(List<Integer> userIds);
}
