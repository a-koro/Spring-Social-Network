package com.connector.beta.services;

import com.connector.beta.projections.MyUserProjection;

import java.util.List;

public interface MessengerServiceInterface {

    public List<MyUserProjection> getChats(Integer userId);
}
