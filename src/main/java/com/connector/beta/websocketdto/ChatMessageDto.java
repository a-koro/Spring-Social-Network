package com.connector.beta.websocketdto;

import java.util.Date;

public class ChatMessageDto {
    private Integer chatMessageId;
    private Integer senderId;
    private Integer recipientId;
//    private String senderEmail;
//    private String recipientEmail;
    private String content;
    private Date timestamp;
    private String status;

    public ChatMessageDto() {
    }

    public ChatMessageDto(Integer chatMessageId, Integer senderId, Integer recipientId, String content, Date timestamp, String status) {
        this.chatMessageId = chatMessageId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Integer getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(Integer chatMessageId) {
        this.chatMessageId = chatMessageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
