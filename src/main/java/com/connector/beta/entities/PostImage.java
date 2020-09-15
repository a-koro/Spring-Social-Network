package com.connector.beta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "post_images")
public class PostImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postImageId;
    private String title;
    @Lob
    @Column(name = "file")
    @JsonIgnore
    private byte[] file;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private String size;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public PostImage() {
    }

    public Integer getPostImageId() {
        return postImageId;
    }

    public void setPostImageId(Integer postImageId) {
        this.postImageId = postImageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
