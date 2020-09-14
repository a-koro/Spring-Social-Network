package com.connector.beta.repos;

import com.connector.beta.dto.PostDto;
import com.connector.beta.projections.PostProjection;
import com.connector.beta.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUserUserIdOrderByCreatedDesc(Integer userId);

    List<Post> findByUserUserIdInOrderByCreatedDesc(List<Integer> userIds);

    List<PostProjection> findAllByUserUserIdIn(List<Integer> postIds);
}
