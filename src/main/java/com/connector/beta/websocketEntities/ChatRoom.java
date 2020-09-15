package com.connector.beta.websocketEntities;

import com.connector.beta.entities.MyUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatRoomId;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessage;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private MyUser sender;
    @ManyToOne
    @JoinColumn(name="recipient_id")
    private MyUser recipient;

    public ChatRoom() {
    }

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public List<ChatMessage> getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(List<ChatMessage> chatMessage) {
        this.chatMessage = chatMessage;
    }

    public MyUser getSender() {
        return sender;
    }

    public void setSender(MyUser sender) {
        this.sender = sender;
    }

    public MyUser getRecipient() {
        return recipient;
    }

    public void setRecipient(MyUser recipient) {
        this.recipient = recipient;
    }


}
