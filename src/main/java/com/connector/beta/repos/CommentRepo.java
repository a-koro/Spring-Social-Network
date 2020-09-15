package com.connector.beta.repos;

import com.connector.beta.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    @Query("SELECT COUNT(c.commentId) FROM Comment c")
    public int getLengthOfComments();
}
