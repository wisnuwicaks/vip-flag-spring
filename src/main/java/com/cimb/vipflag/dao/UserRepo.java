package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Integer> {

}
