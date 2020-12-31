package com.connector.beta.services;

import com.connector.beta.entities.Comment;
import com.connector.beta.entities.MyUser;

import java.util.List;

public interface CommentServiceInterface {

    public void insertComment(Comment comment);

    public void updateComment(Comment comment);

    public Comment findCommentByCommentId(int commentId);

    public void removeComment(Comment comment);

    public int getLengthOfPosts();

    public List<Comment> getNewComments(MyUser user);

    public void markCommentAsViewed(Integer commentId) throws NullPointerException;
}
