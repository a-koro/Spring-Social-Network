package com.connector.beta.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_relationship")
public class UserRelationship implements Serializable {

    @EmbeddedId
    UserRelationshipKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userFirstId")
    @JoinColumn(name = "user_first_id")
    MyUser myUser1;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userSecondId")
    @JoinColumn(name = "user_second_id")
    MyUser myUser2;

    Boolean pendingFirstSecond;

    Boolean pendingSecondFirst;

    Boolean friends;

    public UserRelationship() {
    }

    public UserRelationshipKey getId() {
        return id;
    }

    public void setId(UserRelationshipKey id) {
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

    public Boolean getPendingFirstSecond() {
        return pendingFirstSecond;
    }

    public void setPendingFirstSecond(Boolean pendingFirstSecond) {
        this.pendingFirstSecond = pendingFirstSecond;
    }

    public Boolean getPendingSecondFirst() {
        return pendingSecondFirst;
    }

    public void setPendingSecondFirst(Boolean pendingSecondFirst) {
        this.pendingSecondFirst = pendingSecondFirst;
    }

    public Boolean getFriends() {
        return friends;
    }

    public void setFriends(Boolean friends) {
        this.friends = friends;
    }
}
