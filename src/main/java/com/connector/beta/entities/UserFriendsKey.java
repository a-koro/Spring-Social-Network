package com.connector.beta.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserFriendsKey implements Serializable {

    @Column(name = "user_first_id")
    int userFirstId;

    @Column(name = "user_second_id")
    int userSecondId;

    public UserFriendsKey() {

    }

//    public UserFriendsKey(int userFirstId, int userSecondId) {
//        this.userFirstId = userFirstId;
//        this.userSecondId = userSecondId;
//    }

    public int getUserFirstId() {
        return userFirstId;
    }

    public void setUserFirstId(int userFirstId) {
        this.userFirstId = userFirstId;
    }

    public int getUserSecondId() {
        return userSecondId;
    }

    public void setUserSecondId(int userSecondId) {
        this.userSecondId = userSecondId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFriendsKey that = (UserFriendsKey) o;
        return userFirstId == that.userFirstId &&
                userSecondId == that.userSecondId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userFirstId, userSecondId);
    }
}
