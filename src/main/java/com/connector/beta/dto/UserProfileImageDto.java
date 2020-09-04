package com.connector.beta.dto;


public class UserProfileImageDto {

    private Integer userId;
    private String userProfileImageLink;

    public UserProfileImageDto() {
    }

    public UserProfileImageDto(Integer userId, String userProfileImageLink) {
        this.userId = userId;
        this.userProfileImageLink = userProfileImageLink;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserProfileImageLink() {
        return userProfileImageLink;
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }
}
