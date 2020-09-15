package com.connector.beta.websocketEntities;


import com.connector.beta.entities.MyUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "chat_message")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","sender","recipient","chatRoom"})
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Integer chatMessageId;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private MyUser sender;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    private MyUser recipient;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatRoom chatRoom;

    private String content;
    private Date created;
    @Column(name = "message_status")
    private String status;

    public ChatMessage() {
    }

    public Integer getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(Integer chatMessageId) {
        this.chatMessageId = chatMessageId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
