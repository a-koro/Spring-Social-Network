package com.connector.beta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CommentViewed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentViewedId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Comment comment;
    private Boolean viewed;

    public CommentViewed() {
    }

    @Override
    public String toString() {
        return "CommentViewed{" +
                "commentViewedId=" + commentViewedId +
                ", comment=" + comment +
                ", viewed=" + viewed +
                '}';
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public Integer getCommentViewedId() {
        return commentViewedId;
    }

    public void setCommentViewedId(Integer commentViewedId) {
        this.commentViewedId = commentViewedId;
    }
}
