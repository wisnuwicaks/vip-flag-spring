package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRoleRepo extends JpaRepository<UserRole,Integer> {
    @Query(value = "select * from user_role where role_name=?1",nativeQuery = true)
    public UserRole findUserRoleByRoleName(String roleName);


}
