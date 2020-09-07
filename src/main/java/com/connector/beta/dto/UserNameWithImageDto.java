package com.connector.beta.dto;


import com.connector.beta.entities.Image;


public class UserNameWithImageDto {


    private String firstName;
    private String lastName;
    private Image image;

    public UserNameWithImageDto() {
    }

    public UserNameWithImageDto(String firstName, String lastName, Image image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
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
