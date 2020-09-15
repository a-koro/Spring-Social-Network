package com.connector.beta.websocketServices;

import com.connector.beta.services.UserServiceInterface;
import com.connector.beta.websocketEntities.ChatRoom;
import com.connector.beta.websocketRepo.ChatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepo;
    private final UserServiceInterface userService;

    @Autowired
    public ChatRoomService(ChatRoomRepo chatRoomRepo, UserServiceInterface userService) {
        this.chatRoomRepo = chatRoomRepo;
        this.userService = userService;
    }

   public ChatRoom getChatRoom(Integer sender, Integer recipient){

       Optional<ChatRoom> chatRoom= chatRoomRepo.findBySender_UserIdAndRecipient_UserId(sender,recipient);
       Optional<ChatRoom> chatRoom2= chatRoomRepo.findBySender_UserIdAndRecipient_UserId(recipient,sender);

       if(chatRoom.isPresent()){
           return chatRoom.get();
       }else if(chatRoom2.isPresent()){
           return chatRoom2.get();
       }
       else{
           ChatRoom room = new ChatRoom();
                   room.setSender(userService.findById(sender));
                   room.setRecipient(userService.findById(recipient));
                   room = chatRoomRepo.save(room);
            return room;

       }


   }



}
