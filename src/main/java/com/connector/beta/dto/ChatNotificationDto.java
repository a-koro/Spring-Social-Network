package com.connector.beta.dto;



public class ChatNotificationDto {

    private Integer chatNotificationId;
    private String email;

    public ChatNotificationDto() {
    }

    public ChatNotificationDto(Integer chatNotificationId, String email) {
        this.chatNotificationId = chatNotificationId;
        this.email = email;
    }

    public Integer getChatNotificationId() {
        return chatNotificationId;
    }

    public void setChatNotificationId(Integer chatNotificationId) {
        this.chatNotificationId = chatNotificationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
