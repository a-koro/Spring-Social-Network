package com.connector.beta.repos;

import com.connector.beta.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {

     Optional<Image> findByTitleIgnoreCase(String title);
}
