package com.connector.beta.repos;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.entities.UserRelationship;
import com.connector.beta.entities.UserRelationshipKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRelationshipRepo extends JpaRepository<UserRelationship, UserRelationshipKey> {

    @Query(" SELECT new com.connector.beta.Pojos.UserFriendsDto (ur.id.userFirstId, ur.id.userSecondId, ur.myUser2.email, ur.myUser2.firstName, ur.myUser2.lastName) FROM UserRelationship ur " +
            "JOIN ur.myUser2 m where (ur.id.userFirstId = :myUserId OR ur.id.userSecondId = :myUserId) AND ur.friends = true ")
    List<UserFriendsDto> getAllFriendsWithNames(@Param("myUserId") Integer input);

    @Query(" SELECT new com.connector.beta.Pojos.UserFriendsDto (ur.id.userFirstId, ur.id.userSecondId, ur.myUser1.email, ur.myUser1.firstName, ur.myUser1.lastName) FROM UserRelationship ur " +
            "JOIN ur.myUser1 m where (ur.id.userFirstId = :myUserId OR ur.id.userSecondId = :myUserId) AND ur.friends = true ")
    List<UserFriendsDto> getAllFriendsWithNamesSecond(@Param("myUserId") Integer input);

}
