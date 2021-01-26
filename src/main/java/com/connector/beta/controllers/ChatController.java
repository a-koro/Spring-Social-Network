package com.connector.beta.controllers;

import com.connector.beta.dto.ChatNotificationDto;
import com.connector.beta.entities.MyUser;
import com.connector.beta.mapper.UserMapper;
import com.connector.beta.projections.MyUserProjection;
import com.connector.beta.services.MessengerServiceInterface;
import com.connector.beta.services.UserServiceInterface;
import com.connector.beta.websocketEntities.ChatMessage;

import com.connector.beta.websocketServices.ChatMessageService;
import com.connector.beta.websocketServices.ChatRoomService;
import com.connector.beta.websocketdto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final UserServiceInterface userService;
    private final UserMapper userMapper;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate,
                          ChatMessageService chatMessageService,
                          ChatRoomService chatRoomService, UserServiceInterface userService, UserMapper userMapper) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Autowired
    MessengerServiceInterface messengerServiceInterface;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto messageDto) {



        ChatMessage chatMessage = userMapper.chatMessageDtoToChatMessage(messageDto);


        chatMessage.setSender(userService.findById(messageDto.getSenderId()));
        chatMessage.setRecipient(userService.findById(messageDto.getRecipientId()));
        chatMessage.setCreated(new Date());
           chatMessage.setChatRoom(chatRoomService.getChatRoom(chatMessage.getSender().getUserId(),chatMessage.getRecipient().getUserId()));
           ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipient().getUserId().toString(),
                "/queue/messages",
                new ChatNotificationDto(saved.getChatMessageId(),
                        saved.getSender().getUserId().toString(),
                        saved.getSender().getFirstName(),
                        saved.getSender().getLastName())

        );

    }


    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable Integer senderId,
                                                @PathVariable Integer recipientId) {
        List<ChatMessageDto> messages= userMapper.chatMessageToDtos(chatMessageService.findChatMessages(senderId, recipientId));
        return ResponseEntity
                .ok(messages);
    }


    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable Integer id) {
        ChatMessageDto message =userMapper.chatMessageToDto(chatMessageService.findById(id));
        return ResponseEntity
                .ok(message);
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable Integer senderId,
            @PathVariable Integer recipientId) {

//        MyUser sender = userService.findById(senderId);
//        MyUser recipient = userService.findById(recipientId);
        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/getChats")
    @ResponseBody
    public List<MyUserProjection> getChats(HttpServletRequest request) {
        Integer loggedInUserId = (Integer)request.getSession().getAttribute("loggedInUserId");
        List<MyUserProjection> listOfChats = messengerServiceInterface.getChats(loggedInUserId);
        return listOfChats;
    }
}
