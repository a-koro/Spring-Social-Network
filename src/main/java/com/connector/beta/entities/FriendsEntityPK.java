package com.connector.beta.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FriendsEntityPK implements Serializable {
    private int userFirstId;
    private int userSecondId;

    @Column(name = "user_first_id", nullable = false)
    @Id
    public int getUserFirstId() {
        return userFirstId;
    }

    public void setUserFirstId(int userFirstId) {
        this.userFirstId = userFirstId;
    }

    @Column(name = "user_second_id", nullable = false)
    @Id
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

        FriendsEntityPK that = (FriendsEntityPK) o;

        if (userFirstId != that.userFirstId) return false;
        if (userSecondId != that.userSecondId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userFirstId;
        result = 31 * result + userSecondId;
        return result;
    }
}
