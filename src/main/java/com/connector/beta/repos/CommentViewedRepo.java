package com.connector.beta.repos;

import com.connector.beta.entities.CommentViewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentViewedRepo extends JpaRepository<CommentViewed, Integer> {
}
