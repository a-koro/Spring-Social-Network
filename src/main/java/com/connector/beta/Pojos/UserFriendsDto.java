package com.connector.beta.Pojos;

public class UserFriendsDto {

    private int userFirstId;
    private int userSecondId;
    private String email;
    private String firstName;
    private String lastName;

    public UserFriendsDto() {
    }

    public UserFriendsDto(int userFirstId, int userSecondId, String email, String firstName, String lastName) {
        this.userFirstId = userFirstId;
        this.userSecondId = userSecondId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserFirstId() {
        return userFirstId;
    }

    public void setUserFirstId(int userFirstId) {
        this.userFirstId = userFirstId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserSecondId() {
        return userSecondId;
    }

    public void setUserSecondId(int userSecondId) {
        this.userSecondId = userSecondId;
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
}
