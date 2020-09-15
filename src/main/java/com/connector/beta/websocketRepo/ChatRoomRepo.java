package com.connector.beta.websocketRepo;


import com.connector.beta.entities.MyUser;
import com.connector.beta.websocketEntities.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepo extends JpaRepository<ChatRoom,Integer> {
    Optional<ChatRoom> findBySender_UserIdAndRecipient_UserId(Integer sender, Integer recipient);
}
