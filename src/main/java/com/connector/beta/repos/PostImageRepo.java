package com.connector.beta.repos;

import com.connector.beta.entities.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepo extends JpaRepository<PostImage, Integer> {

    public PostImage findByPostPostId(Integer postId);
}
