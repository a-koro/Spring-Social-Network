package com.connector.beta.websocketRepo;


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
}
