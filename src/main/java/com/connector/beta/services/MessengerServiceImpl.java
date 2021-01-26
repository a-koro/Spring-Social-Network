package com.connector.beta.services;

import com.connector.beta.projections.MyUserProjection;
import com.connector.beta.websocketRepo.ChatMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessengerServiceImpl implements MessengerServiceInterface {

    @Autowired
    ChatMessageRepo chatMessageRepo;

    @Override
    public List<MyUserProjection> getChats(Integer userId) {
        return chatMessageRepo.getAllChatsOrderedByLastMessage(userId);
    }
}
