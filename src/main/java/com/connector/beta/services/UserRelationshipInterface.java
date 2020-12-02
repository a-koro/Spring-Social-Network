package com.connector.beta.services;

import com.connector.beta.projections.MyUserProjection;

import java.util.List;

public interface UserRelationshipInterface {

    public List<MyUserProjection> findAllFriends(int userId);
}
