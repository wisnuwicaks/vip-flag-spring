package com.cimb.vipflag.dao;

import com.cimb.vipflag.entity.AuditTrailAccess;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTrailAccessRepo extends JpaRepository<AuditTrailAccess,Integer> {
}
