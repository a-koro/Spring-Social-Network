package com.connector.beta.entities;

import javax.persistence.*;

@Entity
@Table(name = "friends", schema = "springsummer", catalog = "")
@IdClass(FriendsEntityPK.class)
public class FriendsEntity {
    private int userFirstId;
    private int userSecondId;

    @Id
    @Column(name = "user_first_id", nullable = false)
    public int getUserFirstId() {
        return userFirstId;
    }

    public void setUserFirstId(int userFirstId) {
        this.userFirstId = userFirstId;
    }

    @Id
    @Column(name = "user_second_id", nullable = false)
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

        FriendsEntity that = (FriendsEntity) o;

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
