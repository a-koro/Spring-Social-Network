package com.connector.beta.repos;

import com.connector.beta.entities.FriendsEntity;
import com.connector.beta.entities.FriendsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepo extends JpaRepository<FriendsEntity, FriendsEntityPK> {



}
