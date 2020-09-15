package com.connector.beta.websocketServices;

import com.connector.beta.entities.MyUser;
import com.connector.beta.websocketEntities.ChatMessage;
import com.connector.beta.websocketEntities.ChatRoom;
import com.connector.beta.websocketEntities.MessageStatus;
import com.connector.beta.websocketRepo.ChatMessageRepo;
import com.connector.beta.websocketdto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatMessageService(ChatMessageRepo chatMessageRepo, ChatRoomService chatRoomService) {
        this.chatMessageRepo = chatMessageRepo;
        this.chatRoomService = chatRoomService;
    }


    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepo.save(chatMessage);
    }

    public ChatMessage findById(Integer id) {
        return chatMessageRepo
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED.toString());
                    return chatMessageRepo.save(chatMessage);
                })
                .orElseThrow(()->new IllegalStateException("cant find message "+id));

    }

    public List<ChatMessage> findChatMessages(Integer senderId, Integer recipientId) {
         ChatRoom room = chatRoomService.getChatRoom(senderId,recipientId);

         List<ChatMessage> messages = chatMessageRepo
                 .findChatMessageByChatRoom(room)
                 .orElse(new ArrayList<>());
        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;

    }

    public long countNewMessages(Integer senderId, Integer recipientId) {
        return chatMessageRepo.countBySender_UserIdAndRecipient_UserId(senderId,recipientId);
    }

    private void updateStatuses(Integer senderId,Integer recipientId,MessageStatus status){
                chatMessageRepo.updateStatuses(senderId,recipientId,status.toString());
    }

}
