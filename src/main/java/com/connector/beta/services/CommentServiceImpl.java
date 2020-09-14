package com.connector.beta.services;

import com.connector.beta.entities.Comment;
import com.connector.beta.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentServiceInterface {

    @Autowired
    CommentRepo commentRepo;

    public void insertComment(Comment comment) {
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
}
