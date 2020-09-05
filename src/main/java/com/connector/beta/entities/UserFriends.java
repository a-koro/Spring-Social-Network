package com.connector.beta.entities;


import javax.persistence.*;

@Entity
@Table(name = "users_friends")
public class UserFriends {

    @EmbeddedId
    UserFriendsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userFirstId")
    @JoinColumn(name = "user_first_id")
    MyUser myUser1;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userSecondId")
    @JoinColumn(name = "user_second_id")
    MyUser myUser2;

    int status;

    public UserFriends() {
    }

    public UserFriendsKey getId() {
        return id;
    }

    public void setId(UserFriendsKey id) {
        this.id = id;
    }

    public MyUser getMyUser1() {
        return myUser1;
    }

    public void setMyUser1(MyUser myUser1) {
        this.myUser1 = myUser1;
    }

    public MyUser getMyUser2() {
        return myUser2;
    }

    public void setMyUser2(MyUser myUser2) {
        this.myUser2 = myUser2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserFriends{" +
                "id=" + id +
                ", myUser1=" + myUser1 +
                ", myUser2=" + myUser2 +
                ", status=" + status +
                '}';
    }
}
