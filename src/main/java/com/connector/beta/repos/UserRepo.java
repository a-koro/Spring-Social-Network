/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.repos;

import com.connector.beta.entities.MyUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author korov
 */

@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer> {

    MyUser findByEmail(String email);

    List<MyUser> findByFirstName(String name);
}
