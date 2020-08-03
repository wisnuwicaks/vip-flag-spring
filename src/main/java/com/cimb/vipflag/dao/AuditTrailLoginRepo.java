package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.AuditTrailLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuditTrailLoginRepo extends JpaRepository<AuditTrailLogin,Integer> {
    @Query(value = "SELECT * FROM audit_trail_login WHERE user_id= ?1", nativeQuery = true)
    public AuditTrailLogin findLogByUserId(int userId);

}
