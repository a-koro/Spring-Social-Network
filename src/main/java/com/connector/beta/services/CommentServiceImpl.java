package com.connector.beta.services;

import com.connector.beta.entities.Comment;
import com.connector.beta.entities.CommentViewed;
import com.connector.beta.entities.MyUser;
import com.connector.beta.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentServiceInterface {

    @Autowired
    CommentRepo commentRepo;

    public void insertComment(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public Comment findCommentByCommentId(int commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("No data!"));
        return comment;
    }

    @Override
    public void removeComment(Comment comment) {
        commentRepo.delete(comment);
    }

    @Override
    public int getLengthOfPosts() {
        return commentRepo.getLengthOfComments();
    }

    @Override
    public List<Comment> getNewComments(MyUser user) {
        return commentRepo.findAllByPostUserAndUserNotAndCommentViewedViewedFalseOrderByCreatedAsc(user, user);
    }

    @Override
    public void markCommentAsViewed(Integer commentId) throws NullPointerException {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new NullPointerException("Comment not found"));
        CommentViewed commentViewed = comment.getCommentViewed();
        commentViewed.setViewed(true);
        comment.setCommentViewed(commentViewed);
        commentRepo.save(comment);
    }
}
