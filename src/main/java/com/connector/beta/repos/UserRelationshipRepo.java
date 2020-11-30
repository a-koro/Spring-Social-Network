package com.connector.beta.repos;

import com.connector.beta.Pojos.UserFriendsDto;
import com.connector.beta.entities.MyUser;
import com.connector.beta.entities.UserRelationship;
import com.connector.beta.entities.UserRelationshipKey;
import com.connector.beta.projections.MyUserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRelationshipRepo extends JpaRepository<UserRelationship, UserRelationshipKey> {

//    2 Queries to get all friends of user

    @Query(" SELECT new com.connector.beta.Pojos.UserFriendsDto (ur.id.userFirstId, ur.id.userSecondId, ur.myUser2.email, ur.myUser2.firstName, ur.myUser2.lastName) FROM UserRelationship ur " +
            "JOIN ur.myUser2 m where (ur.id.userFirstId = :myUserId OR ur.id.userSecondId = :myUserId) AND ur.friends = true ")
    List<UserFriendsDto> getAllFriendsWithNames(@Param("myUserId") Integer input);

    @Query(" SELECT new com.connector.beta.Pojos.UserFriendsDto (ur.id.userFirstId, ur.id.userSecondId, ur.myUser1.email, ur.myUser1.firstName, ur.myUser1.lastName) FROM UserRelationship ur " +
            "JOIN ur.myUser1 m where (ur.id.userFirstId = :myUserId OR ur.id.userSecondId = :myUserId) AND ur.friends = true ")
    List<UserFriendsDto> getAllFriendsWithNamesSecond(@Param("myUserId") Integer input);

//    Done

    @Query(" SELECT u FROM UserRelationship u WHERE u.id.userFirstId = :myUserId1 AND u.id.userSecondId = :myUserId2")
    Optional<UserRelationship> getRelationship(
            @Param("myUserId1") Integer myUserId1,
            @Param("myUserId2") Integer myUserId2);

    @Query("SELECT u FROM UserRelationship u WHERE (u.id.userFirstId = :myUserId1 AND u.id.userSecondId = :myUserId2) " +
            "OR (u.id.userFirstId = :myUserId2 AND u.id.userSecondId = :myUserId1)")
    UserRelationship CheckRelationshipIfExists(
            @Param("myUserId1") Integer myUserId1,
            @Param("myUserId2") Integer myUserId2);

    @Query("SELECT u FROM UserRelationship u WHERE (u.id.userFirstId = :myUserId OR u.id.userSecondId = :myUserId) " +
            "AND u.friends = false")
    List<UserRelationship> getAllPendingRelationships(
            @Param("myUserId") Integer myUserId);

    @Modifying
    @Query("delete from UserRelationship u where (u.id.userFirstId = :myUserId1 AND u.id.userSecondId = :myUserId2)")
    void deleteRelationship(
            @Param("myUserId1") Integer myUserId1,
            @Param("myUserId2") Integer myUserId2);


    // Native query method that uses union keyword and returns a projection
    @Query(value = "select u.user_id as userId, u.first_name as firstName, u.last_name as lastName from user_relationship ur " +
            "join " +
            "users u on u.user_id = ur.user_second_id where ur.user_first_id = ? and ur.friends = true " +
            "union " +
            "select u1.user_id as userId, u1.first_name as firstName, u1.last_name as lastName from user_relationship ur1 " +
            "join " +
            "users u1 on u1.user_id = ur1.user_first_id where ur1.user_second_id = ? and ur1.friends = true",
            nativeQuery = true)
    List<MyUserProjection> getAllFriendsWithUnion(Integer myUserId, Integer myUserId2);

}
