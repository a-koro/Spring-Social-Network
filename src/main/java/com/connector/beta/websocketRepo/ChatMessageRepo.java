package com.connector.beta.websocketRepo;


import com.connector.beta.projections.MyUserProjection;
import com.connector.beta.websocketEntities.ChatMessage;
import com.connector.beta.websocketEntities.ChatRoom;

import com.connector.beta.websocketEntities.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage, Integer> {

    Optional<List<ChatMessage>> findChatMessageByChatRoom(ChatRoom room);


    long countBySender_UserIdAndRecipient_UserId(Integer senderId, Integer recipientId);

    @Modifying
    @Query("update ChatMessage m set m.status = :status where m.sender.userId = :sender and m.recipient.userId= :recipient")
    void updateStatuses(@Param("sender") Integer senderId,
                        @Param("recipient") Integer recipientId,
                        @Param("status") String status);

    @Query(value = "select distinct userId, firstName, lastName from (select u.user_id as userId, u.first_name as firstName, u.last_name as lastName, cm.created as created " +
            "from chat_message cm " +
            "join users u on u.user_id = cm.sender_id " +
            "where cm.recipient_id = ?1 " +
            "and u.user_id in (" +
            "select u.user_id from user_relationship ur join users u on u.user_id = ur.user_second_id where ur.user_first_id = ?1 and ur.friends = true " +
            "union " +
            "select u1.user_id from user_relationship ur1 join users u1 on u1.user_id = ur1.user_first_id where ur1.user_second_id = ?1 and ur1.friends = true " +
            ")" +
            "union " +
            "select u1.user_id as userId, u1.first_name as firstName, u1.last_name as lastName, cm1.created as created " +
            "from chat_message cm1 " +
            "join users u1 on u1.user_id = cm1.recipient_id " +
            "where cm1.sender_id = ?1 " +
            "and u1.user_id in (" +
            "select u.user_id from user_relationship ur join users u on u.user_id = ur.user_second_id where ur.user_first_id = ?1 and ur.friends = true " +
            "union " +
            "select u1.user_id from user_relationship ur1 join users u1 on u1.user_id = ur1.user_first_id where ur1.user_second_id = ?1 and ur1.friends = true " +
            ")" +
            "order by created desc) as connections",
            nativeQuery = true)
    public List<MyUserProjection> getAllChatsOrderedByLastMessage(Integer myUserId1);
}
