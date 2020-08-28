package com.connector.beta.repos;

import com.connector.beta.entities.FriendsEntity;
import com.connector.beta.entities.FriendsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepo extends JpaRepository<FriendsEntity, FriendsEntityPK> {

    @Query("select f from FriendsEntity f")
    List<Object[]> getFriendsTable();


}
