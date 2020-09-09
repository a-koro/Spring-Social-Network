package com.connector.beta.dto;

import com.connector.beta.entities.Cheer;
import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.PostImage;

import java.sql.Timestamp;
import java.util.List;

public class PostDto {

    private Integer postId;
    private String text;
    private Timestamp created;
    private String imageUrl;
    private MyUser user;
//    private PostImage postImage;
    private List<Comment> comments;
//    private List<Cheer> cheers;

    public PostDto() {
    }

    public PostDto(Integer postId, String text, Timestamp created, String imageUrl, MyUser user, List<Comment> comments) {
        this.postId = postId;
        this.text = text;
        this.created = created;
        this.imageUrl = imageUrl;
        this.user = user;
//        this.postImage = postImage;
        this.comments = comments;
//        this.cheers = cheers;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

//    public PostImage getPostImage() {
//        return postImage;
//    }
//
//    public void setPostImage(PostImage postImage) {
//        this.postImage = postImage;
//    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

//    public List<Cheer> getCheers() {
//        return cheers;
//    }
//
//    public void setCheers(List<Cheer> cheers) {
//        this.cheers = cheers;
//    }
}
