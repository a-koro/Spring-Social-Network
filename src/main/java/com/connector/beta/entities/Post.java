package com.connector.beta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String text;
    private Timestamp created;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUser user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    List<Cheer> cheers;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL , mappedBy = "post")
    @JsonIgnore
    PostImage postImage;

    public PostImage getPostImage() {
        return postImage;
    }

    public void setPostImage(PostImage postImage) {
        this.postImage = postImage;
    }

    public List<Cheer> getCheers() {
        return cheers;
    }

    public void setCheers(List<Cheer> cheers) {
        this.cheers = cheers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
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
}
