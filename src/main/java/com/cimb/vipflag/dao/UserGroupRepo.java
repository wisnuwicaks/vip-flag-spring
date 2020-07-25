package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.User;
import com.cimb.vipflag.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGroupRepo extends JpaRepository<UserGroup,Integer> {
    @Query(value = "SELECT * FROM user_group WHERE maker_id= ?1", nativeQuery = true)
    public Iterable<UserGroup> findMakerRelation(int makerId);

    @Query(value = "SELECT * FROM user_group WHERE checker_id= ?1", nativeQuery = true)
    public Iterable<UserGroup> findCheckerHasApprove(int makerId);

    @Query(value = "SELECT * FROM user_group WHERE group_name= ?1", nativeQuery = true)
    public Iterable<UserGroup> findGroupByName(String groupName);



}
