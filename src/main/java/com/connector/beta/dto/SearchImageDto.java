package com.connector.beta.dto;



public class SearchImageDto {
    private String firstName;
    private String lastName;
    private int userId;
    private String type;
    private String size;

    public SearchImageDto() {
    }

    public SearchImageDto(String firstName, String lastName, int userId, String type, String size) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.type = type;
        this.size = size;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
