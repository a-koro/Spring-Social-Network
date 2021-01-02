package com.connector.beta.repos;

import com.connector.beta.dto.PostDto;
import com.connector.beta.projections.PostProjection;
import com.connector.beta.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUserUserIdOrderByCreatedDesc(Integer userId);

    List<Post> findByUserUserIdInOrderByCreatedDesc(List<Integer> userIds);

    List<PostProjection> findAllByUserUserIdIn(List<Integer> postIds);

    @Query("SELECT COUNT(p.postId) FROM Post p")
    public int getLengthOfPosts();

    Optional<Post> findByCommentsCommentId(Integer commentId);

    @Query(value = "SELECT * FROM posts p INNER JOIN users u ON p.user_id = u.user_id WHERE p.user_id IN ? LIMIT ?,?", nativeQuery = true)
    List<PostProjection> findAllPostsOfConnectionsFiveByFive(List<Integer> userIds, Integer offset, Integer limit);
}
