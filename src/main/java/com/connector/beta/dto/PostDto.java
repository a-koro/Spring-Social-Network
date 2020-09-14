package com.connector.beta.dto;

import com.connector.beta.entities.Cheer;
import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.PostImage;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class PostDto {

    private Integer postId;
    private String text;
    private Timestamp created;
    private String imageUrl;
    private MyUser user;
    private List<Comment> comments;
    private List<Cheer> cheers;

    public PostDto() {
    }

    public PostDto(Integer postId) {
        this.postId = postId;
    }

    public PostDto(Integer postId, String text, Timestamp created) {
        this.postId = postId;
        this.text = text;
        this.created = created;
    }

    public PostDto(Integer postId, String text, Timestamp created, String imageUrl, MyUser user, List<Comment> comments, List<Cheer> cheers) {
        this.postId = postId;
        this.text = text;
        this.created = created;
        this.imageUrl = imageUrl;
        this.user = user;
        this.comments = comments;
        this.cheers = cheers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return postId.equals(postDto.postId) &&
                Objects.equals(text, postDto.text) &&
                Objects.equals(created, postDto.created) &&
                Objects.equals(imageUrl, postDto.imageUrl) &&
                Objects.equals(user, postDto.user) &&
                Objects.equals(comments, postDto.comments) &&
                Objects.equals(cheers, postDto.cheers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, text, created, imageUrl, user, comments, cheers);
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Cheer> getCheers() {
        return cheers;
    }

    public void setCheers(List<Cheer> cheers) {
        this.cheers = cheers;
    }
}
