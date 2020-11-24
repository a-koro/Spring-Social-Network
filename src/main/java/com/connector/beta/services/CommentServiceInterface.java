package com.connector.beta.services;

import com.connector.beta.entities.Comment;

public interface CommentServiceInterface {

    public void insertComment(Comment comment);

    public void updateComment(Comment comment);

    public Comment findCommentByCommentId(int commentId);

    public void removeComment(Comment comment);

    public int getLengthOfPosts();
}
