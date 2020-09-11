package com.connector.beta.mapper;

import com.connector.beta.dto.ChatNotificationDto;
import com.connector.beta.dto.UserDto;
import com.connector.beta.entities.MyUser;

import com.connector.beta.websocketEntities.ChatMessage;
import com.connector.beta.websocketEntities.ChatNotification;

import com.connector.beta.websocketdto.ChatMessageDto;
import org.mapstruct.*;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MyUser map(UserDto userDto);

    UserDto mapToDto(MyUser user);

    List<UserDto> mapListToDto(List<MyUser> users);

    @Mapping(source ="senderNotification.userId",target = "userId")
    @Mapping(source ="senderNotification.firstName",target = "firstName")
    @Mapping(source ="senderNotification.lastName",target = "lastName")
    ChatNotificationDto mapToChatNotificationDto(ChatNotification chatNotification);

    @Mapping(source = "chatMessageId",target = "chatMessageId")
    @Mapping(source = "sender.userId",target = "senderId")
    @Mapping(source = "recipient.userId",target = "recipientId")
    @Mapping(source = "content",target = "content")
    @Mapping(source = "created",target = "timestamp")
    @Mapping(source = "status",target = "status")
    ChatMessageDto chatMessageToDto(ChatMessage message);

    @Mapping(source = "chatMessageId",target = "chatMessageId")
//    @Mapping(source = "senderId",target = "sender")
//    @Mapping(source = "recipientId",target = "recipient")
    @Mapping(source = "content",target = "content")
    @Mapping(source = "timestamp",target = "created")
    @Mapping(source = "status",target = "status")
    ChatMessage chatMessageDtoToChatMessage(ChatMessageDto chatMessageDto);

    List<ChatMessageDto> chatMessageToDtos(List<ChatMessage> messages);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateChatMessageFromDto(ChatMessageDto chatMessageDto, @MappingTarget ChatMessage chatMessage);

}
