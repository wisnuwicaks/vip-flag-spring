package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * FROM user WHERE username= ?1 and password = ?2", nativeQuery = true)
    public User findUserLogin(String username, String password);

    @Query(value = "SELECT * FROM user WHERE username= ?1", nativeQuery = true)
    public Iterable<User> findAllByUsername(String username);



}
