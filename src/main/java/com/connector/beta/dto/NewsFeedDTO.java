package com.connector.beta.dto;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.entities.Post;
import com.connector.beta.projections.MyUserProjection;

import java.util.List;

public class NewsFeedDTO {

    private List<MyUserProjection> friends;
    private List<Post> posts;

    public NewsFeedDTO() {
    }

    public List<MyUserProjection> getFriends() {
        return friends;
    }

    public void setFriends(List<MyUserProjection> friends) {
        this.friends = friends;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
