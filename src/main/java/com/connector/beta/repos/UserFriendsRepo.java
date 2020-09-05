package com.connector.beta.repos;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.entities.UserFriends;
import com.connector.beta.entities.UserFriendsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFriendsRepo extends JpaRepository<UserFriends, UserFriendsKey> {

    @Query("SELECT uf FROM UserFriends uf JOIN uf.myUser2 m where uf.id.userFirstId = :myUserId")
    List<UserFriends> getAllFriends(@Param("myUserId") Integer input);

    @Query("SELECT new com.connector.beta.Pojos.UserFriendsDto (uf.id.userSecondId, uf.myUser2.firstName, uf.myUser2.lastName) FROM UserFriends uf JOIN uf.myUser2 m where uf.id.userFirstId = :myUserId")
    List<UserFriendsDto> getAllFriendsWithNames(@Param("myUserId") Integer input);

}
