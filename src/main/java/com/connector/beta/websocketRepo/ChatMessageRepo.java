package com.connector.beta.websocketRepo;


import com.connector.beta.websocketEntities.ChatMessage;
import com.connector.beta.websocketEntities.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage, Integer> {

    Optional<List<ChatMessage>> findChatMessageByChatRoom(ChatRoom room);


    long countBySender_UserIdAndRecipient_UserId(Integer senderId, Integer recipientId);

}
