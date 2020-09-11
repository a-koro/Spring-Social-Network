package com.connector.beta.Pojos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRelationshipParams {

    @NotNull
    private int currentUserId;

    @NotNull
    private int profilePageId;

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public int getProfilePageId() {
        return profilePageId;
    }

    public void setProfilePageId(int profilePageId) {
        this.profilePageId = profilePageId;
    }
}
