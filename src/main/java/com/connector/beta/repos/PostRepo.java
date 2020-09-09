package com.connector.beta.repos;

import com.connector.beta.dto.PostDto;
import com.connector.beta.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUserUserIdOrderByCreatedDesc(Integer userId);

    List<Post> findByUserUserIdInOrderByCreatedDesc(List<Integer> userIds);
}
