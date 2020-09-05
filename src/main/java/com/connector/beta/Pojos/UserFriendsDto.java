package com.connector.beta.Pojos;

public class UserFriendsDto {

    private int UserSecondId;
    private String firstName;
    private String lastName;

    public UserFriendsDto() {
    }

    public UserFriendsDto(int userSecondId, String firstName, String lastName) {
        UserSecondId = userSecondId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserSecondId() {
        return UserSecondId;
    }

    public void setUserSecondId(int userSecondId) {
        UserSecondId = userSecondId;
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
