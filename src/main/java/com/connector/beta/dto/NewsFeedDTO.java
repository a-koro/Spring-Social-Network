package com.connector.beta.dto;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.entities.Post;

import java.util.List;

public class NewsFeedDTO {

    private List<UserFriendsDto> friends;
    private List<Post> posts;

    public NewsFeedDTO() {
    }

    public List<UserFriendsDto> getFriends() {
        return friends;
    }

    public void setFriends(List<UserFriendsDto> friends) {
        this.friends = friends;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
