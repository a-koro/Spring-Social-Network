package com.connector.beta.dto;


import com.connector.beta.entities.Image;


public class UserNameWithImageDto {


    private Integer userId;
    private String firstName;
    private String lastName;
    private Image image;

    public UserNameWithImageDto() {
    }

    public UserNameWithImageDto(Integer userId, String firstName, String lastName, Image image) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
