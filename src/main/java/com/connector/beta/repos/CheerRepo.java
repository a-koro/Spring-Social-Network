package com.connector.beta.repos;

import com.connector.beta.entities.Cheer;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheerRepo extends JpaRepository<Cheer, Integer> {

    Optional<Cheer> findByUserAndPost(MyUser user, Post post);

    List<Cheer> findByPost(Post post);
}
